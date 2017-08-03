package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;

public class FlattenSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public FlattenSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return SequenceMatcherResult.match(result.getJump(), sequenceMatcherRequest.text(result.getJump()));
        } else {
            return result;
        }
    }

    @Override
    public Explanation explain() {
        return sequenceMatcher.explain();
    }
}
