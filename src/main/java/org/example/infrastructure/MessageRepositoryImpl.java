package org.example.infrastructure;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.domain.InstrumentName;
import org.example.domain.InstrumentPrice;

import java.io.IOException;
import java.util.*;

import static java.util.Optional.ofNullable;

class MessageRepositoryImpl implements MessageRepository, Message {

    private static final EnumMap<InstrumentName, InstrumentPrice> INSTRUMENT_PRICE_LATEST_MAP = new EnumMap<>(InstrumentName.class);

    private static final CsvMapper mapper;
    private static final CsvSchema schema;

    static {
        mapper = new CsvMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(CsvParser.Feature.TRIM_SPACES);
        schema = mapper.schemaFor(InstrumentPrice.class).withoutHeader().withColumnSeparator(',').withLineSeparator("\n");
    }

    @Override
    public void onMessage(String message) {
        List<InstrumentPrice> list = parseFromCsv(message);
        getLatestPrices(list);
    }

    private List<InstrumentPrice> parseFromCsv(String message) {
        try {
            MappingIterator<InstrumentPrice> mappingIterator = mapper.readerFor(InstrumentPrice.class).with(schema).readValues(message);
            return mappingIterator.readAll(); //I assume that message doesn't contain a billion line which cause out of memory
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The algorithm can definitely be improved
     */
    private void getLatestPrices(List<InstrumentPrice> list) {
        Collections.reverse(list);
        for (InstrumentName instrumentName : InstrumentName.values()) {
            for (InstrumentPrice instrumentPrice : list) {
                if (instrumentName.getValue().equals(instrumentPrice.getName().getValue())) {
                    INSTRUMENT_PRICE_LATEST_MAP.put(instrumentName, instrumentPrice);
                    break;
                }
            }
        }
    }

    @Override
    public Optional<InstrumentPrice> getPrice(InstrumentName instrumentName) {
        return ofNullable(INSTRUMENT_PRICE_LATEST_MAP.get(instrumentName));
    }

    @Override
    public Collection<InstrumentPrice> getAllPrices() {
        return INSTRUMENT_PRICE_LATEST_MAP.values();
    }

}
