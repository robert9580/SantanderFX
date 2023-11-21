package org.example.domain;

public interface PriceService {

    InstrumentPrice getPriceWithCommission(InstrumentName instrumentName);
}
