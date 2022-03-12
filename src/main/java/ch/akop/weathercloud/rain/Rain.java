package ch.akop.weathercloud.rain;

import ch.akop.weathercloud.light.LightUnit;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents light. Possible units are in {@link LightUnit}.
 */
public record Rain(BigDecimal baseValue) {

    private static final RainUnit BASE_UNIT = RainUnit.MILLIMETER_PER_HOUR;

    @NotNull
    public static Rain fromUnit(@NotNull BigDecimal value, @NotNull RainUnit unit) {
        return new Rain(unit.convertTo(value, unit, BASE_UNIT));
    }

    @Override
    public String toString() {
        return "%s %s".formatted(
                this.baseValue.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros(),
                BASE_UNIT.getTextSuffix());
    }
}
