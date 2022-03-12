package ch.akop.weather.api.light;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
public enum LightUnit {

    WATT_PER_SQUARE_METER("W/m²", value -> value, value -> value);

    @Getter
    private final String textSuffix;

    private final UnaryOperator<BigDecimal> fromWattPerSquareMeter;
    private final UnaryOperator<BigDecimal> toWattPerSquareMeter;


    public BigDecimal convertTo(BigDecimal value, LightUnit fromUnit, LightUnit toUnit) {
        var wmm = fromUnit.toWattPerSquareMeter.apply(value);
        return toUnit.fromWattPerSquareMeter.apply(wmm);
    }
}
