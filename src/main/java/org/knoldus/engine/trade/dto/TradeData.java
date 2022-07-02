package org.knoldus.engine.trade.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@Data
public class TradeData {
    private String tradeId;
    private String direction;
    private double quantity;
    private double allocationAmount;
    private double confirmationAmount;
    private String eb;
    private String pb;

    @JsonCreator
    public TradeData(
            @JsonProperty("tradeId") String tradeId,
            @JsonProperty("direction") String direction,
            @JsonProperty("quantity") double quantity,
            @JsonProperty("allocationAmount") double allocationAmount,
            @JsonProperty("confirmationAmount") double confirmationAmount,
            @JsonProperty("eb") String eb,
            @JsonProperty("pb") String pb) {
        this.tradeId = tradeId;
        this.direction = direction;
        this.quantity = quantity;
        this.allocationAmount = allocationAmount;
        this.confirmationAmount = confirmationAmount;
        this.eb = eb;
        this.pb = pb;
    }
}
