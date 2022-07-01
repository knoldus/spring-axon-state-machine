package org.knoldus.engine.bucket.command;

import org.knoldus.engine.bucket.dto.BucketMasterSyn;

public class MasterLockCommand extends BaseCommand<String>{
    private final BucketMasterSyn bucketMasterSyn;
    public MasterLockCommand(String id, BucketMasterSyn bucketMasterSyn) {
        super(id);
        this.bucketMasterSyn = bucketMasterSyn;
    }

    public BucketMasterSyn getBucketMasterSyn() {
        return bucketMasterSyn;
    }

}
