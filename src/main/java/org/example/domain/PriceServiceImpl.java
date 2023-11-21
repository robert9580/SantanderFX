package org.example.domain;

import org.example.infrastructure.MessageRepository;

import java.math.BigDecimal;

import static org.example.domain.CommissionUtil.addPercentage;
import static org.example.domain.CommissionUtil.subtractPercentage;

//@Service
class PriceServiceImpl implements PriceService {

    private static final BigDecimal BID_COMMISSION = new BigDecimal("0.001");
    private static final BigDecimal ASK_COMMISSION = new BigDecimal("0.001");

    private final MessageRepository messageRepository;

    PriceServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public InstrumentPrice getPriceWithCommission(InstrumentName instrumentName) {
        return messageRepository.getPrice(instrumentName).map(this::addCommission)
                .orElseThrow(() -> new IllegalArgumentException("price of '" + instrumentName + "' not found"));
    }

    private InstrumentPrice addCommission(InstrumentPrice instrumentPrice) {
        return instrumentPrice.toBuilder()
                .ask(addPercentage(instrumentPrice.getAsk(), ASK_COMMISSION))
                .bid(subtractPercentage(instrumentPrice.getBid(), BID_COMMISSION))
                .build();
    }

}
