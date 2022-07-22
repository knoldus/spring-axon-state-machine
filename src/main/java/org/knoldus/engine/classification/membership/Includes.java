package org.knoldus.engine.classification.membership;

import java.util.List;
import java.util.stream.Collectors;

public class Includes implements NodeData {

    private List<String> elements;

    public Includes(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        String include = "Include(%s)";
        return String.format(include, elements);
    }

    @Override
    public boolean has(String value) {
        return elements.contains(value);
    }

    @Override
    public boolean isIntersect(NodeData nodeData) {

        Includes includes = nodeData instanceof Includes ? ((Includes) nodeData) : null;

        if (includes == null)
            return false;

        return elements.stream().distinct().
                filter(includes.elements::contains).collect(Collectors.toSet()).isEmpty();

    }
}
