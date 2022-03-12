package ch.akop.weather.api.wind;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static ch.akop.weather.api.wind.WindSpeedUnit.KILOMETERS_PER_SECOND;
import static ch.akop.weather.api.wind.WindSpeedUnit.METERS_PER_SECOND;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WindTest {

    @Test
    void test_windSpeed_to_kmh() {
        // arrange
        var ms = BigDecimal.valueOf(10.5);
        var expectedKmh = BigDecimal.valueOf(37.8);

        // act
        var result = Wind.fromUnit(ms, METERS_PER_SECOND).getAs(KILOMETERS_PER_SECOND);

        // assert
        assertEquals(expectedKmh.stripTrailingZeros(), result.stripTrailingZeros());

    }

}
