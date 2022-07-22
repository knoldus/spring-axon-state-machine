package org.knoldus.engine.classification.membership;

public class All implements NodeData {


    @Override
    public String toString() {
        return "All";
    }

    @Override
    public boolean has(String value) {
        return true;
    }

    @Override
    public boolean isIntersect(NodeData nodeData) {
        return false;
    }
}
