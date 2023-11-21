package org.example.domain;

import org.example.infrastructure.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    private static final LocalDateTime NOW = LocalDateTime.now();

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void shouldReturnPriceWithCommission() {
        //given
        when(messageRepository.getPrice(InstrumentName.GBP_USD)).thenReturn(Optional.of(instrumentPrice()));

        //when
        InstrumentPrice result = priceService.getPriceWithCommission(InstrumentName.GBP_USD);

        //then
        InstrumentPriceAssert.assertThat(result)
                .hasId(150L)
                .hasName(InstrumentName.GBP_USD)
                .hasBid(new BigDecimal("1.2487"))
                .hasAsk(new BigDecimal("1.2574"))
                .hasTimestamp(NOW);
    }

    private InstrumentPrice instrumentPrice() {
        return InstrumentPrice.builder()
                .id(150L)
                .name(InstrumentName.GBP_USD)
                .bid(new BigDecimal("1.2499"))
                .ask(new BigDecimal("1.2561"))
                .timestamp(NOW)
                .build();
    }

    @Test
    void shouldThrow() {
        //given
        when(messageRepository.getPrice(InstrumentName.EUR_PLN)).thenReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> priceService.getPriceWithCommission(InstrumentName.EUR_PLN));

        //then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("price of '" + InstrumentName.EUR_PLN + "' not found");
    }

}
