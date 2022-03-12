package ch.akop.weather.api.temperature;


import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static ch.akop.weather.api.temperature.TemperatureUnit.DEGREE;


public record Temperature(@NotNull BigDecimal baseValue) {


    private static final TemperatureUnit BASE_UNIT = DEGREE;

    @NotNull
    public static Temperature fromUnit(@NotNull BigDecimal value, @NotNull TemperatureUnit unit) {
        return new Temperature(unit.convertTo(value, unit, BASE_UNIT));
    }

    @NotNull
    public BigDecimal getAs(@NotNull TemperatureUnit unit) {
        return unit.convertTo(this.baseValue, BASE_UNIT, unit);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(
                this.baseValue.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros(),
                BASE_UNIT.getTextSuffix());
    }
}
