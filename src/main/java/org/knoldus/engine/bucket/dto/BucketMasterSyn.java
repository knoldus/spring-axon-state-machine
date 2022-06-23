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
    private Bucket bucket;
    private long time;

    @JsonCreator
    public BucketMasterSyn(
            @JsonProperty("cutoff") boolean cutoff,
            @JsonProperty("bucket") Bucket bucket,
            @JsonProperty("time") long time) {

        this.cutoff = cutoff;
        this.bucket = bucket;
        this.time = time;
    }
}
