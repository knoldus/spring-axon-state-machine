package org.knoldus.engine.trade.command;

import org.knoldus.engine.trade.dto.TradeData;

public class CreateTradeCommand extends BaseCommand<String> {

    private final TradeData tradeData;

    public CreateTradeCommand(String id, TradeData tradeData) {
        super(id);
        this.tradeData = tradeData;
    }

    public TradeData getTradeData() {
        return tradeData;
    }
}
