package ch.akop.weather.api.wind;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static ch.akop.weather.api.wind.WindSpeedUnit.METERS_PER_SECOND;


public record Wind(BigDecimal baseValue) {

    private static final WindSpeedUnit BASE_UNIT = METERS_PER_SECOND;

    @NotNull
    public static Wind fromUnit(@NotNull BigDecimal value, @NotNull WindSpeedUnit unit) {
        return new Wind(unit.convertTo(value, unit, BASE_UNIT));
    }

    @NotNull
    public BigDecimal getAs(@NotNull WindSpeedUnit unit) {
        return unit.convertTo(this.baseValue, BASE_UNIT, unit);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(
                this.baseValue.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros(),
                BASE_UNIT.getTextSuffix());
    }
}
