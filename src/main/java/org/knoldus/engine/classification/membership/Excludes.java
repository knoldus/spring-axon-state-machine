package org.knoldus.engine.classification.membership;

import java.util.List;
import java.util.stream.Collectors;

public class Excludes implements NodeData {

    private List<String> elements;

    public Excludes(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        String exclude = "Exclude(%s)";
        return String.format(exclude, elements);
    }

    @Override
    public boolean has(String value) {
        return !elements.contains(value);
    }

    @Override
    public boolean isIntersect(NodeData nodeData) {

        Excludes excludes = nodeData instanceof Excludes ? ((Excludes) nodeData) : null;

        if (excludes == null)
            return false;

        return elements.stream().distinct().
                filter(excludes.elements::contains)
                .collect(Collectors.toSet()).isEmpty();

    }
}
