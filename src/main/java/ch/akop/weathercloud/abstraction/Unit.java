package ch.akop.weathercloud.abstraction;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public interface Unit {

    default BigDecimal convertTo(BigDecimal value, Unit toUnit) {
        var wmm = this.getToDefault().apply(value);
        return toUnit.getFromDefault().apply(wmm);
    }

    UnaryOperator<BigDecimal> getFromDefault();

    UnaryOperator<BigDecimal> getToDefault();

    String getTextSuffix();
}
