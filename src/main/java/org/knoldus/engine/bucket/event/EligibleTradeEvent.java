package org.knoldus.engine.bucket.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knoldus.engine.bucket.dto.TradeAd;


public class EligibleTradeEvent extends BaseEvent<String> {

    private final TradeAd tradeAd;
    @JsonCreator
    public EligibleTradeEvent(@JsonProperty("id") String id,
                              @JsonProperty("tradeAd") TradeAd tradeAd) {
        super(id);
        this.tradeAd = tradeAd;
    }

    public TradeAd getTradeAd() {
        return tradeAd;
    }
}
