package org.knoldus.engine.bucket.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knoldus.engine.bucket.dto.BucketMasterSyn;

public class MasterLocked extends BaseEvent<String> {

    private final BucketMasterSyn bucketMasterSyn;
    @JsonCreator
    public MasterLocked(@JsonProperty("id") String id,
                        @JsonProperty("bucketMasterSyn") BucketMasterSyn bucketMasterSyn) {
        super(id);
        this.bucketMasterSyn = bucketMasterSyn;
    }

    public BucketMasterSyn getBucketMasterSyn() {
        return bucketMasterSyn;
    }
}
