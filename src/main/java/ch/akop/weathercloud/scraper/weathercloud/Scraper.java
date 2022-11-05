package ch.akop.weathercloud.scraper.weathercloud;

import ch.akop.weathercloud.Weather;
import ch.akop.weathercloud.light.Light;
import ch.akop.weathercloud.light.LightUnit;
import ch.akop.weathercloud.rain.Rain;
import ch.akop.weathercloud.rain.RainUnit;
import ch.akop.weathercloud.temperature.Temperature;
import ch.akop.weathercloud.temperature.TemperatureUnit;
import ch.akop.weathercloud.wind.Wind;
import ch.akop.weathercloud.wind.WindSpeedUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Use this scraper to fetch data from Weathercloud.
 */
public class Scraper {

    private static final String BASE_URL = "https://app.weathercloud.net/device/stats?code=";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new SimpleModule().addDeserializer(TimestampPair.class, new CustomDeserializerTimestampPairType()));


    /**
     * Fetch all data of a specific device. You can discover devices on <a href="https://app.weathercloud.net">this site</a>.
     * <p>
     * Each device has an ID, you can see it, when you open the device in a webbrowser and copy the id from the url.
     *
     * @param deviceId numeric deviceId, like 1234567890
     * @return Parsed weather-data
     */
    @SneakyThrows
    public Weather scrape(String deviceId) {
        String strResponse = getResponseFromWeatherCloud(deviceId);
        var response = Scraper.mapper.readValue(strResponse, Response.class);

        return new Weather()
                .setRecordedAt(ZonedDateTime.ofInstant(Instant.ofEpochSecond(response.lastUpdate()), ZoneId.systemDefault()))
                .setWind(Wind.fromUnit(response.wspdCurrent().value(), WindSpeedUnit.METERS_PER_SECOND))
                .setOuterTemperatur(Temperature.fromUnit(response.tempCurrent().value(), TemperatureUnit.DEGREE))
                .setLight(Light.fromUnit(response.solarradCurrent().value(), LightUnit.KILO_LUX))
                .setRain(Rain.fromUnit(response.rainrateCurrent().value(), RainUnit.MILLIMETER_PER_HOUR));
    }

    @SneakyThrows
    private String getResponseFromWeatherCloud(String deviceId) {

        final var delayOnException = Duration.ofSeconds(10);
        int retryCounter = 0;
        Exception lastException;

        do {
            try {
                return doRequestAndGetBody(deviceId);
            } catch (InterruptedException e) {
                // abort on interruption
                throw e;
            } catch (Exception e) {
                // retry with an increasing backoff
                Thread.sleep(delayOnException.toMillis() * ++retryCounter);
                lastException = e;
            }
        } while (retryCounter < 10);

        throw new RuntimeException("Can't talk to weatherCloud even after %d retries.".formatted(retryCounter), lastException);
    }

    private String doRequestAndGetBody(String deviceId) throws URISyntaxException, IOException, InterruptedException {
        final var request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(BASE_URL + deviceId))
                .header("X-Requested-With", "XMLHttpRequest")
                .timeout(Duration.ofSeconds(10))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    /**
     * Pull weather-data at a constant rate. It will fetch the first dataset immediately.
     *
     * @param deviceId numeric deviceId, like 1234567890
     * @param rate     Duration, how often data should be fetched.
     * @return A flowable, which will emit on each new value (same values will not be emitted twice)
     */
    public Flowable<Weather> scrape$(String deviceId, Duration rate) {
        // because of backoff-logic, we need to prevent that the timer-emits will stack up
        AtomicBoolean isWorking = new AtomicBoolean(false);
        return Observable.interval(0, rate.toMillis(), TimeUnit.MILLISECONDS, Schedulers.io())
                .filter(aLong -> !isWorking.get())
                .flatMap(ignored -> {
                    isWorking.set(true);

                    return Observable.fromSupplier(() -> this.scrape(deviceId))
                            .doOnComplete(() -> isWorking.set(false))
                            .subscribeOn(Schedulers.computation());
                })
                .distinct(Weather::getRecordedAt)
                .toFlowable(BackpressureStrategy.DROP);
    }
}
