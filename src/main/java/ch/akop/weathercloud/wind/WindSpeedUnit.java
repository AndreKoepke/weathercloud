package ch.akop.weathercloud.wind;

import ch.akop.weathercloud.abstraction.Unit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import static java.math.RoundingMode.HALF_UP;

@RequiredArgsConstructor
@Getter
public enum WindSpeedUnit implements Unit {

    METERS_PER_SECOND("m/s", value -> value, value -> value),
    KILOMETERS_PER_SECOND("km/h", ms -> ms.multiply(BigDecimal.valueOf(3.6)), kmh -> kmh.divide(BigDecimal.valueOf(3.6), HALF_UP));

    private final String textSuffix;
    private final UnaryOperator<BigDecimal> fromDefault;
    private final UnaryOperator<BigDecimal> toDefault;
}
