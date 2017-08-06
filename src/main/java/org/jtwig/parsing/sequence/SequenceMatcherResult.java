package org.jtwig.parsing.sequence;

import com.google.common.base.Optional;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SequenceMatcherResult {
    public static SequenceMatcherResult mismatch () {
        return new SequenceMatcherResult(SequenceMatcherResultType.MISMATCH, 0, Optional.<MatchResult>absent());
    }

    public static SequenceMatcherResult match (int jump, MatchResult result) {
        return new SequenceMatcherResult(SequenceMatcherResultType.MATCHED, jump, Optional.of(result));
    }

    public static SequenceMatcherResult error(SequenceMatcherRequest sequenceMatcherRequest) {
        return new SequenceMatcherResult(SequenceMatcherResultType.ERROR, sequenceMatcherRequest.getOffset(), Optional.<MatchResult>absent());
    }

    private final SequenceMatcherResultType type;
    private final int jump;
    private final Optional<MatchResult> match;

    public SequenceMatcherResult(SequenceMatcherResultType type, int jump, Optional<MatchResult> match) {
        this.type = type;
        this.jump = jump;
        this.match = match;
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

    public Optional<MatchResult> getMatchResult() {
        return match;
    }

    public SequenceMatcherResult withJump(int jump) {
        return new SequenceMatcherResult(type, jump, match);
    }
}
