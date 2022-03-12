package ch.akop.weather.api.temperature;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static ch.akop.weather.api.temperature.TemperatureUnit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TemperatureTest {

    @Test
    void test_degree_to_kelvin() {
        // arrange
        var degree = BigDecimal.valueOf(23.7);
        var expectedKelvin = BigDecimal.valueOf(296.85);

        // act
        var result = Temperature.fromUnit(degree, DEGREE).getAs(KELVIN);

        // assert
        assertEquals(expectedKelvin.stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    void test_degree_to_fahrenheit() {
        // arrange
        var degree = BigDecimal.valueOf(23.7);
        var expectedFahrenheit = BigDecimal.valueOf(74.66);

        // act
        var result = Temperature.fromUnit(degree, DEGREE).getAs(FAHRENHEIT);

        // assert
        assertEquals(expectedFahrenheit.stripTrailingZeros(), result.stripTrailingZeros());
    }

}
