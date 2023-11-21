package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InstrumentName {
    EUR_USD("EUR/USD"),
    EUR_JPY("EUR/JPY"),
    GBP_USD("GBP/USD"),
    EUR_PLN("EUR/PLN");

    private final String value;

    InstrumentName(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static InstrumentName ofValue(String value) {
        for(InstrumentName instrumentName : InstrumentName.values()) {
            if (instrumentName.getValue().equals(value)) {
                return instrumentName;
            }
        }
        throw new IllegalArgumentException(String.format("Value '%s' not recognized", value));
    }

    @Override
    public String toString() {
        return value;
    }
}
