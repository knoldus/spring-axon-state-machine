package org.knoldus.engine.classification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.knoldus.engine.classification.membership.*;
import org.knoldus.engine.trade.dto.GroupKey;
import org.knoldus.engine.trade.dto.GroupPathAndKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


class ClassificationTreeTest {


    @Test
    public void test_insertPath_in_tree() {

        ArrayList<NodeData> path1 = new ArrayList<>();
        path1.add(new Exact("Eb1"));
        path1.add(new Exact("pb1"));
        path1.add(new Includes(new ArrayList<>(Arrays.asList("IM1", "IM2"))));
        path1.add(new Includes(new ArrayList<>(Arrays.asList("Fund1", "Fund2"))));
        path1.add(new Includes(new ArrayList<>(List.of("Sedol1"))));
        path1.add(new Includes(new ArrayList<>(Arrays.asList("USD", "ILS"))));
        path1.add(new Exact("Exchange1"));
        path1.add(new All());

        List<NodeData> path2 = new LinkedList<>();
        path2.add(new Exact("Eb1"));
        path2.add(new Exact("pb1"));
        path2.add(new Exact("IM1")) ;
        path2.add(new Excludes(new ArrayList<>(List.of("Fund1"))));
        path2.add(new Includes(new ArrayList<>(List.of("Sedol1"))));
        path2.add(new Includes(new ArrayList<>(Arrays.asList("USD", "ILS"))));
        path2.add(new Exact("Exchange1"));
        path2.add(new All());

        GroupKey groupKey1 = new GroupKey("123", 1);
        GroupKey groupKey2 = new GroupKey("456", 1);
        GroupPathAndKey groupPathAndKey1 = new GroupPathAndKey(path1, groupKey1);
        GroupPathAndKey groupPathAndKey2 = new GroupPathAndKey(path2, groupKey2);

        List<GroupPathAndKey> groupPathAndKeys = new LinkedList<>();
        groupPathAndKeys.add(groupPathAndKey1);
        groupPathAndKeys.add(groupPathAndKey2);

        ClassificationTree classificationTree =
                new ClassificationTree(new TreeNode.Branch(new Root(),
                        new LinkedList<>()));

        groupPathAndKeys.forEach(path ->
                classificationTree.insertPath(path.getGroupPath(), path.getGroupKey()));
        Assertions.assertEquals(groupPathAndKeys.size(), classificationTree.size);

    }

}