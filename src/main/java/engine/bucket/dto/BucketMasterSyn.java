package engine.bucket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BucketMasterSyn {

    private boolean cutoff;
    private Bucket bucket;
    private long time;
}
