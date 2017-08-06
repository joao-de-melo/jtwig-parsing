package org.jtwig.parsing.model;

import org.jtwig.parsing.model.tree.ContentNode;
import org.jtwig.parsing.model.tree.Node;

public class MatchResult {
    private final Range range;
    private final Node node;

    public MatchResult(Range range, Node node) {
        this.range = range;
        this.node = node;
    }

    public Range getRange() {
        return range;
    }

    public Node getNode() {
        return node;
    }

    public MatchResult with (Node newNode) {
        return new MatchResult(range, newNode);
    }

    public <T> T as (Class<T> type) {
        return type.cast(((ContentNode) node).getContent());
    }
}
