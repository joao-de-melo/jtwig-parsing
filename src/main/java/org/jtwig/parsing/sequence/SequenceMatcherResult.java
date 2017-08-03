package org.jtwig.parsing.sequence;

import com.google.common.base.Optional;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jtwig.parsing.tree.Node;

public class SequenceMatcherResult {
    public static SequenceMatcherResult mismatch () {
        return new SequenceMatcherResult(SequenceMatcherResultType.MISMATCH, 0, Optional.<Node>absent());
    }

    public static SequenceMatcherResult match (int jump, Node node) {
        return new SequenceMatcherResult(SequenceMatcherResultType.MATCHED, jump, Optional.of(node));
    }

    public static SequenceMatcherResult error(SequenceMatcherRequest sequenceMatcherRequest) {
        return new SequenceMatcherResult(SequenceMatcherResultType.ERROR, sequenceMatcherRequest.getOffset(), Optional.<Node>absent());
    }

    private final SequenceMatcherResultType type;
    private final int jump;
    private final Optional<Node> node;

    public SequenceMatcherResult(SequenceMatcherResultType type, int jump, Optional<Node> node) {
        this.type = type;
        this.jump = jump;
        this.node = node;
    }

    public boolean matched() {
        return type == SequenceMatcherResultType.MATCHED;
    }
    public boolean isMismatch() {
        return type == SequenceMatcherResultType.MISMATCH;
    }

    public int getJump() {
        return jump;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public SequenceMatcherResultType getType() {
        return type;
    }

    public boolean isError() {
        return type == SequenceMatcherResultType.ERROR;
    }

    public Optional<Node> getNode() {
        return node;
    }

    public SequenceMatcherResult withJump(int jump) {
        return new SequenceMatcherResult(type, jump, node);
    }
}
