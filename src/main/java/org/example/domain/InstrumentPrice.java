package org.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Maybe considered separate classes for domain and infrastructure
 */
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@JsonPropertyOrder({ "id", "name", "bid", "ask", "timestamp" })
public class InstrumentPrice {
    private Long id;
    private InstrumentName name;
    private BigDecimal bid;
    private BigDecimal ask;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss:SSS")
    private LocalDateTime timestamp;

    /**
     * important for jackson deserialization
     */
    @SuppressWarnings("unused")
    InstrumentPrice() {
    }
}
