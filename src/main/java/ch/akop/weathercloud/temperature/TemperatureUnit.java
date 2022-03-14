package ch.akop.weathercloud.temperature;

import ch.akop.weathercloud.abstraction.Unit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import static ch.akop.weathercloud.temperature.ConversionUtil.*;

@RequiredArgsConstructor
@Getter
public enum TemperatureUnit implements Unit {

    DEGREE("°C", value -> value, value -> value),
    FAHRENHEIT("°F", FAHRENHEIT_TO_DEGREES, DEGREES_TO_FAHRENHEIT),
    KELVIN("K", KELVIN_TO_DEGREES, DEGREES_TO_KELVIN);


    private final String textSuffix;
    private final UnaryOperator<BigDecimal> toDefault;
    private final UnaryOperator<BigDecimal> fromDefault;
}
