package org.knoldus.engine.trade.dto;

import lombok.*;
import org.knoldus.engine.classification.membership.NodeData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupPathAndKey {

    private List<NodeData> groupPath;
    private GroupKey groupKey;
}
