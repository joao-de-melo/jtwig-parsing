package org.jtwig.parsing.sequence;

import org.jtwig.parsing.transform.Transformation;

public class TransformSequenceMatcher<T> implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;
    private final Transformation<T> transformation;

    public TransformSequenceMatcher(SequenceMatcher sequenceMatcher, Transformation<T> transformation) {
        this.sequenceMatcher = sequenceMatcher;
        this.transformation = transformation;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return result.withNode(transformation.transform(result.getMatchResult()));
        }

        return result;
    }
}
