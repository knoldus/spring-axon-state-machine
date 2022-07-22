package org.knoldus.engine.classification;

import org.knoldus.engine.classification.TreeNode.Branch;
import org.knoldus.engine.classification.membership.NodeData;
import org.knoldus.engine.trade.dto.GroupKey;

import java.util.LinkedList;
import java.util.List;

/**
 * Classification tree implementation.
 */
public class ClassificationTree {

    private static final int HEAD = 0;
    private static final int TAIL = 1;
    private final Branch branch;
    public int size;

    public ClassificationTree(Branch root) {
        this.branch = root;
    }

    public void insertPath(final List<NodeData> groupPath, final GroupKey groupKey) {

        insertPathInTree(branch, groupPath, groupKey);
        size += 1;
    }

    /**
     * Insert group path in the existing tree.
     *
     * @param currentNode current node of tree either Branch or Leaf
     * @param groupPath   list of [[NodeData]] as a group path to insert.
     * @param groupKey    [[GroupKey]] groupId and revision.
     * @return [[TreeNode]] tree after group path insertion.
     */
    private TreeNode insertPathInTree(TreeNode currentNode,
                                      List<NodeData> groupPath,
                                      GroupKey groupKey) {

        Branch currentBranch = toBranch(currentNode);
        if (groupPath.isEmpty()) {

            TreeNode.Leaf leaf = new TreeNode.Leaf(groupKey);
            return currentBranch.addChild(leaf);
        }
        TreeNode newNode = currentBranch.getChildren().stream().filter(
                        treeNode -> toBranch(treeNode).getValue().toString()
                                .equals(groupPath.get(HEAD).toString()))
                .findFirst().orElseGet(() -> currentBranch.addChild(
                        new Branch(groupPath.get(HEAD), new LinkedList<>())));

        return insertPathInTree(newNode,
                groupPath.subList(TAIL, groupPath.size()), groupKey);
    }

    private Branch toBranch(TreeNode treeNode) {
        return treeNode instanceof Branch ? ((Branch) treeNode) : null;
    }
}
