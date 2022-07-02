package org.knoldus.engine.bucket.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knoldus.engine.bucket.dto.Bucket;

public class BucketCreated extends BaseEvent<String> {

    private final Bucket bucket;
    @JsonCreator
    public BucketCreated(@JsonProperty("id") String id,
                         @JsonProperty("bucket") Bucket bucket) {
        super(id);
        this.bucket = bucket;
    }

    public Bucket getBucket() {
        return bucket;
    }
}
