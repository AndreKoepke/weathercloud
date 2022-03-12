package ch.akop.weather.api.wind;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import static java.math.RoundingMode.HALF_UP;

@RequiredArgsConstructor
public enum WindSpeedUnit {

    METERS_PER_SECOND("m/s", value -> value, value -> value),
    KILOMETERS_PER_SECOND("km/h", ms -> ms.multiply(BigDecimal.valueOf(3.6)), kmh -> kmh.divide(BigDecimal.valueOf(3.6), HALF_UP));

    @Getter
    private final String textSuffix;
    private final UnaryOperator<BigDecimal> fromMetersPerSecond;
    private final UnaryOperator<BigDecimal> toMetersPerSecond;

    public BigDecimal convertTo(BigDecimal value, WindSpeedUnit fromUnit, WindSpeedUnit toUnit) {
        var ms = fromUnit.toMetersPerSecond.apply(value);
        return toUnit.fromMetersPerSecond.apply(ms);
    }

}
