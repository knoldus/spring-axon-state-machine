package org.knoldus.engine.classification.membership;

public class Root implements NodeData {

    @Override
    public String toString() {
        return "root";
    }

    @Override
    public boolean has(String value) {
        return false;
    }

    @Override
    public boolean isIntersect(NodeData nodeData) {
        return false;
    }
}
