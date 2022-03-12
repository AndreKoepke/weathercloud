package ch.akop.weathercloud.temperature;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.UnaryOperator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ConversionUtil {

    private static final BigDecimal KELVIN = BigDecimal.valueOf(273.15);

    /**
     * °C = (°F - 32) / 1.8
     */
    public static final UnaryOperator<BigDecimal> FAHRENHEIT_TO_DEGREES = fahrenheit -> fahrenheit
            .subtract(BigDecimal.valueOf(32))
            .divide(BigDecimal.valueOf(1.8), RoundingMode.HALF_UP);

    /**
     * °F = C° * 1.8 + 32
     */
    public static final UnaryOperator<BigDecimal> DEGREES_TO_FAHRENHEIT = degrees -> degrees
            .multiply(BigDecimal.valueOf(1.8))
            .add(BigDecimal.valueOf(32));

    public static final UnaryOperator<BigDecimal> DEGREES_TO_KELVIN = degrees -> degrees.add(KELVIN);
    public static final UnaryOperator<BigDecimal> KELVIN_TO_DEGREES = kelvin -> kelvin.subtract(KELVIN);

}
