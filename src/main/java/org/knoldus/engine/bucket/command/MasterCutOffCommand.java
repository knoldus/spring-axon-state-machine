package org.knoldus.engine.bucket.command;


import org.knoldus.engine.bucket.dto.BucketMasterSyn;

import java.io.Serializable;

public class MasterCutOffCommand extends BaseCommand<String> implements Serializable {


    private final BucketMasterSyn bucketMasterSyn;
    public MasterCutOffCommand(String id, BucketMasterSyn bucketMasterSyn) {
        super(id);
        this.bucketMasterSyn = bucketMasterSyn;
    }

    public BucketMasterSyn getBucketMasterSyn() {
        return bucketMasterSyn;
    }
}
