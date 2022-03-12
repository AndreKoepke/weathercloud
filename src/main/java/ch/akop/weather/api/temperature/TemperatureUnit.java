package ch.akop.weather.api.temperature;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import static ch.akop.weather.api.temperature.ConversionUtil.*;

@RequiredArgsConstructor
public enum TemperatureUnit {

    DEGREE("°C", value -> value, value -> value),
    FAHRENHEIT("°F", FAHRENHEIT_TO_DEGREES, DEGREES_TO_FAHRENHEIT),
    KELVIN("K", KELVIN_TO_DEGREES, DEGREES_TO_KELVIN);


    @Getter
    private final String textSuffix;

    private final UnaryOperator<BigDecimal> toDegrees;
    private final UnaryOperator<BigDecimal> fromDegrees;


    public BigDecimal convertTo(BigDecimal value, TemperatureUnit fromUnit, TemperatureUnit toUnit) {
        var degrees = fromUnit.toDegrees.apply(value);
        return toUnit.fromDegrees.apply(degrees);
    }
}
