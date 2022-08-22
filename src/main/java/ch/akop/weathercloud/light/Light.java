package ch.akop.weathercloud.light;

import ch.akop.weathercloud.abstraction.ValueWithUnit;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Represents light. Possible units are in {@link LightUnit}.
 */
public class Light extends ValueWithUnit<LightUnit> {

    private static final LightUnit BASE_UNIT = LightUnit.KILO_LUX;

    public Light(BigDecimal valueAsBaseUnit) {
        super(BASE_UNIT, valueAsBaseUnit);
    }

    @NotNull
    public static Light fromUnit(@NotNull BigDecimal value, @NotNull LightUnit unit) {
        return new Light(unit.convertTo(value, BASE_UNIT));
    }

}
