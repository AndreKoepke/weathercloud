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
import io.reactivex.rxjava3.observers.TestObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@Slf4j
class ScraperTest {

    Scraper testee;

    @BeforeEach
    void setup() {
        testee = spy(new Scraper());
    }

    @Test
    void manual_test() {
        var result = testee.scrape("7003523537");
        assertNotNull(result);
    }

    @Test
    public void manual_scheduler_test() throws InterruptedException {
        var value1 = new Weather()
                .setRecordedAt(ZonedDateTime.now())
                .setLight(Light.fromUnit(BigDecimal.ZERO, LightUnit.KILO_LUX))
                .setOuterTemperatur(Temperature.fromUnit(BigDecimal.ZERO, TemperatureUnit.DEGREE))
                .setWind(Wind.fromUnit(BigDecimal.ZERO, WindSpeedUnit.KILOMETERS_PER_SECOND))
                .setRain(Rain.fromUnit(BigDecimal.ZERO, RainUnit.MILLIMETER_PER_HOUR));

        var value2 = new Weather()
                .setRecordedAt(ZonedDateTime.now().plusHours(12))
                .setLight(Light.fromUnit(BigDecimal.ONE, LightUnit.KILO_LUX))
                .setOuterTemperatur(Temperature.fromUnit(BigDecimal.ONE, TemperatureUnit.DEGREE))
                .setWind(Wind.fromUnit(BigDecimal.ONE, WindSpeedUnit.KILOMETERS_PER_SECOND))
                .setRain(Rain.fromUnit(BigDecimal.ONE, RainUnit.MILLIMETER_PER_HOUR));


        doReturn(value1)
                .doThrow(new RuntimeException("blub"))
                .doReturn(value1)
                .doReturn(value2)
                .when(testee)
                .scrape(anyString());


        var tester = new TestObserver<Weather>();
        Scraper.scrape$("7003523537", Duration.of(1, ChronoUnit.SECONDS), testee)
                .doOnError(e -> log.warn("Hi"))
                .toObservable()
                .subscribe(tester);

        Thread.sleep(100);
        tester.assertValuesOnly(value1);
        Thread.sleep(1500);

        tester.assertComplete();
    }

}
