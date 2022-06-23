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
    private String classificationId;
    private String direction;
    private double quantity;
    private double allocationAmount;
    private double confirmationAmount;
    private Bucket bucket;

    @JsonCreator
    public TradeAd(
            @JsonProperty("tradeId") String tradeId,
            @JsonProperty("isTradeEligible") boolean tradeEligible,
            @JsonProperty("classificationId") String classificationId,
            @JsonProperty("direction") String direction,
            @JsonProperty("quantity") double quantity,
            @JsonProperty("allocationAmount") double allocationAmount,
            @JsonProperty("confirmationAmount") double confirmationAmount,
            @JsonProperty("bucket") Bucket bucket) {
        this.tradeId = tradeId;
        this.tradeEligible = tradeEligible;
        this.classificationId = classificationId;
        this.direction = direction;
        this.quantity = quantity;
        this.allocationAmount = allocationAmount;
        this.confirmationAmount = confirmationAmount;
        this.bucket = bucket;
    }
}
