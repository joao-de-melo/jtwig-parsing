package org.jtwig.parsing.sequence;

import org.jtwig.parsing.transform.Transformation;

public class TransformSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;
    private final Transformation transformation;

    public TransformSequenceMatcher(SequenceMatcher sequenceMatcher, Transformation transformation) {
        this.sequenceMatcher = sequenceMatcher;
        this.transformation = transformation;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return SequenceMatcherResult.match(result.getJump(), transformation.transform(result.getMatchResult().get()));
        }

        return result;
    }
}
