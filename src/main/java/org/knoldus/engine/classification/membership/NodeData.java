package org.knoldus.engine.classification.membership;

public interface NodeData {

    public boolean has(String value);

    public boolean isIntersect(NodeData nodeData);
}
