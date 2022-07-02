package org.knoldus.engine.bucket.command;

import org.knoldus.engine.bucket.dto.Bucket;
import org.knoldus.engine.bucket.dto.BucketMasterSyn;

public class CreateBucketCommand extends BaseCommand<String> {

    private final Bucket bucket;

    public CreateBucketCommand(String id, Bucket bucket) {
        super(id);
        this.bucket = bucket;
    }

    public Bucket getBucket() {
        return bucket;
    }
}
