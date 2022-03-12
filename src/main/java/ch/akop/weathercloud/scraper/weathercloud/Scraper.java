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
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
     * Fetch all data of a specific device. You can discover devices on this site https://app.weathercloud.net.
     * <p>
     * Each device has an ID, you can see it, when you open the device in a webbrowser and copy the id from the url.
     *
     * @param deviceId numeric url, like 1234567890
     * @return Parsed weather-data
     */
    @SneakyThrows
    public Weather scrape(String deviceId) {
        final var request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(BASE_URL + deviceId))
                .header("X-Requested-With", "XMLHttpRequest")
                .build();

        var strResponse = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        var response = Scraper.mapper.readValue(strResponse, Response.class);

        return new Weather()
                .setRecordedAt(ZonedDateTime.ofInstant(Instant.ofEpochSecond(response.lastUpdate()), ZoneId.systemDefault()))
                .setWind(Wind.fromUnit(response.wspdCurrent().value(), WindSpeedUnit.METERS_PER_SECOND))
                .setOuterTemperatur(Temperature.fromUnit(response.tempCurrent().value(), TemperatureUnit.DEGREE))
                .setLight(Light.fromUnit(response.solarradCurrent().value(), LightUnit.WATT_PER_SQUARE_METER))
                .setRain(Rain.fromUnit(response.rainCurrent().value(), RainUnit.MILLIMETER_PER_HOUR));
    }

}