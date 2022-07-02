package org.knoldus.engine.trade.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knoldus.engine.bucket.dto.Bucket;
import org.knoldus.engine.trade.dto.TradeData;

public class TradeCreated extends BaseEvent<String> {

    private final TradeData tradeData;
    @JsonCreator
    public TradeCreated(@JsonProperty("id") String id,
                        @JsonProperty("tradeData") TradeData tradeData) {
        super(id);
        this.tradeData = tradeData;
    }

    public TradeData getTradeData() {
        return tradeData;
    }
}
