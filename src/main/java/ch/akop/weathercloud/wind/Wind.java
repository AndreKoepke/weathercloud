package ch.akop.weathercloud.wind;

import ch.akop.weathercloud.abstraction.ValueWithUnit;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Represents wind-speed. Possible units are in {@link WindSpeedUnit}.
 */
public class Wind extends ValueWithUnit<WindSpeedUnit> {

    private static final WindSpeedUnit BASE_UNIT = WindSpeedUnit.METERS_PER_SECOND;

    private Wind(BigDecimal valueAsBaseUnit) {
        super(BASE_UNIT, valueAsBaseUnit);
    }

    @NotNull
    public static Wind fromUnit(@NotNull BigDecimal value, @NotNull WindSpeedUnit unit) {
        return new Wind(unit.convertTo(value, BASE_UNIT));
    }

}
