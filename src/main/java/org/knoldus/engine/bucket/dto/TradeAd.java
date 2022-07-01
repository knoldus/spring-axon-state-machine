package org.knoldus.engine.bucket.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@Data
public class TradeAd {
    private String tradeId;
    private boolean tradeEligible;
    private String direction;
    private double quantity;
    private double allocationAmount;
    private double confirmationAmount;
    private String eb;
    private String pb;
    private String bucketId;

    @JsonCreator
    public TradeAd(
            @JsonProperty("tradeId") String tradeId,
            @JsonProperty("isTradeEligible") boolean tradeEligible,
            @JsonProperty("direction") String direction,
            @JsonProperty("quantity") double quantity,
            @JsonProperty("allocationAmount") double allocationAmount,
            @JsonProperty("confirmationAmount") double confirmationAmount,
            @JsonProperty("eb") String eb,
            @JsonProperty("pb") String pb,
            @JsonProperty("bucketId") String bucketId) {
        this.tradeId = tradeId;
        this.tradeEligible = tradeEligible;
        this.direction = direction;
        this.quantity = quantity;
        this.allocationAmount = allocationAmount;
        this.confirmationAmount = confirmationAmount;
        this.eb = eb;
        this.pb = pb;
        this.bucketId = bucketId;
    }
}
