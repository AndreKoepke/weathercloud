package ch.akop.weathercloud.rain;

import ch.akop.weathercloud.abstraction.Unit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
@Getter
public enum RainUnit implements Unit {

    MILLIMETER_PER_HOUR("mm/h", value -> value, value -> value);


    private final String textSuffix;
    private final UnaryOperator<BigDecimal> fromDefault;
    private final UnaryOperator<BigDecimal> toDefault;

}
