package ch.akop.weathercloud.temperature;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static ch.akop.weathercloud.temperature.TemperatureUnit.*;
import static org.junit.jupiter.api.Assertions.*;


class TemperatureTest {

    @Test
    void test_conversion_is_correct() {
        // arrange
        var temp1 = Temperature.fromUnit(BigDecimal.valueOf(23.7), DEGREE);
        var temp2 = BigDecimal.valueOf(19);

        // act and assert
        assertTrue(temp1.isBiggerThan(temp2, DEGREE));
        assertFalse(temp1.isSmallerThan(temp2, DEGREE));
        assertNotEquals(temp1, Temperature.fromUnit(temp2, DEGREE));
        assertEquals(temp1, Temperature.fromUnit(BigDecimal.valueOf(23.7), DEGREE));
    }

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
