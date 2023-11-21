package org.example.infrastructure;

import org.example.domain.InstrumentName;
import org.example.domain.InstrumentPrice;

import java.util.Collection;
import java.util.Optional;

public interface MessageRepository {

    Optional<InstrumentPrice> getPrice(InstrumentName instrumentName);

    Collection<InstrumentPrice> getAllPrices();
}
