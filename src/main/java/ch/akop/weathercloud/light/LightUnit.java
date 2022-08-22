package ch.akop.weathercloud.light;

import ch.akop.weathercloud.abstraction.Unit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
@Getter
public enum LightUnit implements Unit {

    KILO_LUX("kLux", value -> value, value -> value);

    private final String textSuffix;
    private final UnaryOperator<BigDecimal> fromDefault;
    private final UnaryOperator<BigDecimal> toDefault;
}
