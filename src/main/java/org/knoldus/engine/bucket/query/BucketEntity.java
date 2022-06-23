package org.knoldus.engine.bucket.query;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.knoldus.engine.bucket.machine.BucketState;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Data
@JsonSerialize
public class BucketEntity {

    private static final long SerialVersionUID = 10L;

    @Id
    private String bucketId;
    private String tradeId;
    private String classificationId;
    private String direction;
    private double quantity;
    private double allocationAmount;
    private double confirmationAmount;
    private BucketState bucketState;

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getAllocationAmount() {
        return allocationAmount;
    }

    public void setAllocationAmount(double allocationAmount) {
        this.allocationAmount = allocationAmount;
    }

    public double getConfirmationAmount() {
        return confirmationAmount;
    }

    public void setConfirmationAmount(double confirmationAmount) {
        this.confirmationAmount = confirmationAmount;
    }

    public BucketState getBucketState() {
        return bucketState;
    }

    public void setBucketState(BucketState bucketState) {
        this.bucketState = bucketState;
    }
}
