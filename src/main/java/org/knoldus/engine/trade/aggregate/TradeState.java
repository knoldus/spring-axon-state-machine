package org.knoldus.engine.trade.aggregate;

public enum TradeState {

    INIT,
    PENDING,
    NETTED,
    NETTED_LOCKED,
    REMOVED,
    IN_ELIGIBLE
}
