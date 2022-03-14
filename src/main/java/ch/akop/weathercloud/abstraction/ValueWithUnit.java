package ch.akop.weathercloud.abstraction;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ValueWithUnit<T extends Unit> {

    private final T baseUnit;
    private final BigDecimal valueAsBaseUnit;

    protected ValueWithUnit(T unit, BigDecimal valueAsBaseUnit) {
        this.baseUnit = unit;
        this.valueAsBaseUnit = valueAsBaseUnit;
    }

    public BigDecimal getAs(@NotNull T unit) {
        return this.baseUnit.convertTo(this.valueAsBaseUnit, unit);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(
                this.valueAsBaseUnit.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros(),
                this.baseUnit.getTextSuffix());
    }

}
