package ch.akop.weathercloud.temperature;


import ch.akop.weathercloud.abstraction.ValueWithUnit;
import ch.akop.weathercloud.rain.RainUnit;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import static ch.akop.weathercloud.temperature.TemperatureUnit.DEGREE;

/**
 * Represents light. Possible units are in {@link RainUnit}.
 */
public class Temperature extends ValueWithUnit<TemperatureUnit> {

    private static final TemperatureUnit BASE_UNIT = DEGREE;

    private Temperature(BigDecimal valueAsBaseUnit) {
        super(BASE_UNIT, valueAsBaseUnit);
    }

    @NotNull
    public static Temperature fromUnit(@NotNull BigDecimal value, @NotNull TemperatureUnit unit) {
        return new Temperature(unit.convertTo(value, BASE_UNIT));
    }

}
