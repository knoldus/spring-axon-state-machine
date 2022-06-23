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

    private String uid;
    private String groupId;
    private String groupKind;
    private String eb;
    private String pb;

    @JsonCreator
    public Bucket(@JsonProperty("uid") String uid,
                  @JsonProperty("groupId") String groupId,
                  @JsonProperty("groupKind") String groupKind,
                  @JsonProperty("eb") String eb,
                  @JsonProperty("pb") String pb) {
        this.uid = uid;
        this.groupId = groupId;
        this.groupKind = groupKind;
        this.eb = eb;
        this.pb = pb;
    }
}
