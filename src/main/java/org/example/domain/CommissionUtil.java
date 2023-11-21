package org.example.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CommissionUtil {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    public static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
        return base.multiply(pct, new MathContext(4, RoundingMode.HALF_EVEN));
    }

    public static BigDecimal addPercentage(BigDecimal base, BigDecimal pct) {
        return base.add(base.multiply(pct, new MathContext(2, RoundingMode.HALF_EVEN)));
    }

    public static BigDecimal subtractPercentage(BigDecimal base, BigDecimal pct) {
        return base.subtract(base.multiply(pct, new MathContext(2, RoundingMode.HALF_EVEN)));
    }
}
