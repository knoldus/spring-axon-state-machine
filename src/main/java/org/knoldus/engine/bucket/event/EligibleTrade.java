package org.knoldus.engine.bucket.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knoldus.engine.bucket.dto.EligibleTradeData;


public class EligibleTrade extends BaseEvent<String> {

    private final EligibleTradeData eligibleTradeData;
    @JsonCreator
    public EligibleTrade(@JsonProperty("id") String id,
                         @JsonProperty("tradeAd") EligibleTradeData eligibleTradeData) {
        super(id);
        this.eligibleTradeData = eligibleTradeData;
    }

    public EligibleTradeData getTradeAd() {
        return eligibleTradeData;
    }
}
