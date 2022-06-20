package engine.bucket.command;

import engine.bucket.dto.TradeAd;

public class TradeEligibleCommand extends BaseCommand<String> {

    private final TradeAd tradeAd;
    public TradeEligibleCommand(String id, TradeAd tradeAd) {
        super(id);
        this.tradeAd = tradeAd;
    }

    public TradeAd getTradeAd() {
        return tradeAd;
    }
}
