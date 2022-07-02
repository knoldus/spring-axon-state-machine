package org.knoldus.engine.bucket.command;

import org.knoldus.engine.bucket.dto.EligibleTradeData;

import java.io.Serializable;

public class TradeEligibleCommand extends BaseCommand<String> implements Serializable {

    private final EligibleTradeData tradeAd;
    public TradeEligibleCommand(String id, EligibleTradeData tradeAd) {
        super(id);
        this.tradeAd = tradeAd;
    }

    public EligibleTradeData getTradeAd() {
        return tradeAd;
    }
}
