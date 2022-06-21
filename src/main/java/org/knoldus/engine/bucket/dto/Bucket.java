package org.knoldus.engine.bucket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Bucket {

    private String uid;
    private String groupId;
    private String groupKind;
    private String eb;
    private String pb;
}
