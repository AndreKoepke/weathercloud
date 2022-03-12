package ch.akop.weather.api.rain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
public enum RainUnit {

    MILLIMETER_PER_HOUR("mm/h", value -> value, value -> value);

    @Getter
    private final String textSuffix;

    private final UnaryOperator<BigDecimal> fromMillimeterPerHour;
    private final UnaryOperator<BigDecimal> toMillimeterPerHour;


    public BigDecimal convertTo(BigDecimal value, RainUnit fromUnit, RainUnit toUnit) {
        var wmm = fromUnit.toMillimeterPerHour.apply(value);
        return toUnit.fromMillimeterPerHour.apply(wmm);
    }
}
