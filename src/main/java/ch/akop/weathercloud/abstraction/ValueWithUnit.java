package ch.akop.weathercloud.abstraction;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
@EqualsAndHashCode
public class ValueWithUnit<T extends Unit> {

    @EqualsAndHashCode.Exclude
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

    public int compareTo(BigDecimal valueAsBaseUnit) {
        return this.valueAsBaseUnit.compareTo(valueAsBaseUnit);
    }

    public int compareTo(BigDecimal value, Unit unit) {
        return compareTo(unit.convertTo(value, baseUnit));
    }

    public boolean isBiggerThan(BigDecimal value, Unit unit) {
        return compareTo(value, unit) > 0;
    }

    public boolean isBiggerThan(int value, Unit unit) {
        return isBiggerThan(BigDecimal.valueOf(value), unit);
    }

    public boolean isSmallerThan(BigDecimal value, Unit unit) {
        return compareTo(value, unit) < 0;
    }

    public boolean isSmallerThan(double value, Unit unit) {
        return isSmallerThan(BigDecimal.valueOf(value), unit);
    }
}
