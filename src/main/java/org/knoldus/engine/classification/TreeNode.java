package org.knoldus.engine.classification;

import org.knoldus.engine.classification.membership.NodeData;
import org.knoldus.engine.trade.dto.GroupKey;

import java.util.List;

/**
 * Represent node of a tree - either a Branch or a Leaf
 */
public interface TreeNode {

    final class Branch implements TreeNode {

        private final List<TreeNode> children;
        private final NodeData value;
        public Branch(NodeData nodeData, List<TreeNode> children) {
            this.children = children;
            this.value = nodeData;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        /**
         * Add nodes as its list of children
         *
         * @param treeNode [[TreeNode]] either Branch or Leaf
         * @return [[TreeNode]]
         */
        public TreeNode addChild(final TreeNode treeNode) {

            children.add(0, treeNode);
            return treeNode;
        }

        public NodeData getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Branch(" + value +
                    "," + children +
                    ')';
        }
    }

    final class Leaf implements TreeNode {

        private GroupKey groupKey;
        public Leaf(GroupKey groupKey) {
            this.groupKey = groupKey;
        }

        @Override
        public String toString() {
            return groupKey.getId() + "_" + groupKey.getRevision();
        }
    }
}
