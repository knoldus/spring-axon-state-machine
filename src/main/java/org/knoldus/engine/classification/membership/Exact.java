package org.knoldus.engine.classification.membership;

public class Exact implements NodeData {

    private String element;

    public Exact(String element) {
        this.element = element;
    }

    @Override
    public String toString() {
        String exact = "Exact(%s)";
        return String.format(exact, element);
    }

    @Override
    public boolean has(String value) {
        return element.equals(value);
    }

    @Override
    public boolean isIntersect(NodeData nodeData) {
        return false;
    }
}
