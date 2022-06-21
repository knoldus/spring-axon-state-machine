package org.knoldus.engine.bucket.event;

import org.knoldus.engine.bucket.dto.TradeAd;

import java.io.Serializable;

public class EligibleTradeEvent extends BaseEvent<String> implements Serializable {

    private final TradeAd tradeAd;
    public EligibleTradeEvent(String id, TradeAd tradeAd) {
        super(id);
        this.tradeAd = tradeAd;
    }

    public TradeAd getTradeAd() {
        return tradeAd;
    }
}
