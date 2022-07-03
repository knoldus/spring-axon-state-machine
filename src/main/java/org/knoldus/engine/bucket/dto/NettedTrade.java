package org.knoldus.engine.bucket.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class NettedTrade {

    private String direction;
    private double quantity;
    private double netAmount;
    private List<EligibleTradeData> trades;

}
