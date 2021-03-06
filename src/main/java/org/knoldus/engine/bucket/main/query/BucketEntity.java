package org.knoldus.engine.bucket.main.query;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.knoldus.engine.bucket.main.aggregate.BucketState;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;


@Data
@JsonSerialize
public class BucketEntity implements Serializable {
    @Id
    private String bucketId;
    private List<String> tradeIds;
    private BucketState bucketState;

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public List<String> getTradeIds() {
        return tradeIds;
    }

    public void setTradeIds(List<String> tradeId) {
        this.tradeIds = tradeId;
    }

    public BucketState getBucketState() {
        return bucketState;
    }

    public void setBucketState(BucketState bucketState) {
        this.bucketState = bucketState;
    }
}
