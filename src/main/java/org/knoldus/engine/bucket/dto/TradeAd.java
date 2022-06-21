package org.knoldus.engine.bucket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TradeAd {
    private String tradeId;
    private boolean tradeEligible;
    private String classificationId;
    private String direction;
    private double quantity;
    private double allocationAmount;
    private double confirmationAmount;
    private Bucket bucket;
}
