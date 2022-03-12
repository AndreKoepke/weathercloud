package ch.akop.weather.api.light;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static ch.akop.weather.api.light.LightUnit.WATT_PER_SQUARE_METER;

/**
 * Represents light. Possible units are in {@link LightUnit}.
 */
public record Light(BigDecimal baseValue) {

    private static final LightUnit BASE_UNIT = WATT_PER_SQUARE_METER;

    @NotNull
    public static Light fromUnit(@NotNull BigDecimal value, @NotNull LightUnit unit) {
        return new Light(unit.convertTo(value, unit, BASE_UNIT));
    }

    @Override
    public String toString() {
        return "%s %s".formatted(
                this.baseValue.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros(),
                BASE_UNIT.getTextSuffix());
    }
}
