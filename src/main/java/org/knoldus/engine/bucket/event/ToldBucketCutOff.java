package org.knoldus.engine.bucket.event;

import org.knoldus.engine.bucket.dto.BucketMasterSyn;

import java.io.Serializable;

public class ToldBucketCutOff extends BaseEvent<String> implements Serializable {

    private final BucketMasterSyn bucketMasterSyn;
    public ToldBucketCutOff(String id, BucketMasterSyn bucketMasterSyn) {
        super(id);
        this.bucketMasterSyn = bucketMasterSyn;
    }

    public BucketMasterSyn getBucketMasterSyn() {
        return bucketMasterSyn;
    }
}