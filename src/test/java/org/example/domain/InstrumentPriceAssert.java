package org.example.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InstrumentPriceAssert extends AbstractAssert<InstrumentPriceAssert, InstrumentPrice> {
    private InstrumentPriceAssert(InstrumentPrice actual) {
        super(actual, InstrumentPriceAssert.class);
    }

    public static InstrumentPriceAssert assertThat(InstrumentPrice actual) {
        return new InstrumentPriceAssert(actual);
    }

    public InstrumentPriceAssert hasId(Long id) {
        Assertions.assertThat(actual.getId()).isEqualTo(id);
        return this;
    }

    public InstrumentPriceAssert hasName(InstrumentName name) {
        Assertions.assertThat(actual.getName()).isSameAs(name);
        return this;
    }

    public InstrumentPriceAssert hasBid(BigDecimal bid) {
        Assertions.assertThat(actual.getBid()).isEqualTo(bid);
        return this;
    }

    public InstrumentPriceAssert hasAsk(BigDecimal ask) {
        Assertions.assertThat(actual.getAsk()).isEqualTo(ask);
        return this;
    }

    public InstrumentPriceAssert hasTimestamp(LocalDateTime timestamp) {
        Assertions.assertThat(actual.getTimestamp()).isEqualTo(timestamp);
        return this;
    }

}
