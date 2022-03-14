package ch.akop.weathercloud.rain;

import ch.akop.weathercloud.abstraction.ValueWithUnit;
import ch.akop.weathercloud.light.LightUnit;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Represents light. Possible units are in {@link LightUnit}.
 */
public class Rain extends ValueWithUnit<RainUnit> {

    private static final RainUnit BASE_UNIT = RainUnit.MILLIMETER_PER_HOUR;

    private Rain(BigDecimal valueInBaseUnit) {
        super(BASE_UNIT, valueInBaseUnit);
    }

    @NotNull
    public static Rain fromUnit(@NotNull BigDecimal value, @NotNull RainUnit unit) {
        return new Rain(unit.convertTo(value, BASE_UNIT));
    }
}
