package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;
import org.jtwig.parsing.transform.Transformation;

public class TransformSequenceMatcher<O> implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;
    private final Transformation<O> transformation;

    public TransformSequenceMatcher(SequenceMatcher sequenceMatcher, Transformation<O> transformation) {
        this.sequenceMatcher = sequenceMatcher;
        this.transformation = transformation;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return SequenceMatcherResult.match(result.getJump(), transformation.transform(result.getNode().get()));
        }

        return result;
    }

    @Override
    public Explanation explain() {
        return sequenceMatcher.explain();
    }
}
