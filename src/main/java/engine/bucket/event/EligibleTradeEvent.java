package engine.bucket.event;

import engine.bucket.dto.TradeAd;

public class EligibleTradeEvent extends BaseEvent<String> {

    private final TradeAd tradeAd;
    public EligibleTradeEvent(String id, TradeAd tradeAd) {
        super(id);
        this.tradeAd = tradeAd;
    }

    public TradeAd getTradeAd() {
        return tradeAd;
    }
}
