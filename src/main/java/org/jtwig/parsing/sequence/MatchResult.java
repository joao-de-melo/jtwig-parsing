package org.jtwig.parsing.sequence;

import org.jtwig.parsing.tree.Node;

public class MatchResult {
    private final int startOffset;
    private final int endOffset;
    private final Node node;

    public MatchResult(int startOffset, int endOffset, Node node) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.node = node;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    public Node getNode() {
        return node;
    }
}
