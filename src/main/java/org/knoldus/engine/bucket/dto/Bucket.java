package org.knoldus.engine.bucket.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Bucket {

    private String bucketId;

    private Long cutOff;

    private Long lock;

    @JsonCreator
    public Bucket(@JsonProperty("bucketId") String bucketId,
                  @JsonProperty("cutOff") Long cutOff,
                  @JsonProperty("lock") Long lock) {
        this.bucketId = bucketId;
        this.cutOff = cutOff;
        this.lock = lock;
    }
}
