package org.knoldus.engine.bucket.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@Data
public class BucketMasterSyn {
    private boolean cutoff;

    private boolean lock;
    private String bucketId;
    private long time;

    @JsonCreator
    public BucketMasterSyn(
            @JsonProperty("cutoff") boolean cutoff,
            @JsonProperty("lock") boolean lock,
            @JsonProperty("bucketId") String bucketId,
            @JsonProperty("time") long time) {

        this.cutoff = cutoff;
        this.lock = lock;
        this.bucketId = bucketId;
        this.time = time;
    }
}
