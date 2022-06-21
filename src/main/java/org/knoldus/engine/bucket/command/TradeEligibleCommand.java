package org.knoldus.engine.bucket.command;

import org.knoldus.engine.bucket.dto.TradeAd;

import java.io.Serializable;

public class TradeEligibleCommand extends BaseCommand<String> implements Serializable {

    private final TradeAd tradeAd;
    public TradeEligibleCommand(String id, TradeAd tradeAd) {
        super(id);
        this.tradeAd = tradeAd;
    }

    public TradeAd getTradeAd() {
        return tradeAd;
    }
}
