package org.jtwig.parsing.model.tree;

import java.util.List;

public class ListNode extends Node {
    private final List<Node> nodes;

    public ListNode(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
