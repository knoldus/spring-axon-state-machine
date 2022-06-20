package engine.bucket.event;

import engine.bucket.dto.BucketMasterSyn;

public class ToldBucketCutOff extends BaseEvent<String> {

    private final BucketMasterSyn bucketMasterSyn;
    public ToldBucketCutOff(String id, BucketMasterSyn bucketMasterSyn) {
        super(id);
        this.bucketMasterSyn = bucketMasterSyn;
    }

    public BucketMasterSyn getBucketMasterSyn() {
        return bucketMasterSyn;
    }
}