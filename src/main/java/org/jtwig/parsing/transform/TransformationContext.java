package org.jtwig.parsing.transform;

public class TransformationContext {
    private final int startOffset;
    private final int endOffset;

    public TransformationContext(int startOffset, int endOffset) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }
}
