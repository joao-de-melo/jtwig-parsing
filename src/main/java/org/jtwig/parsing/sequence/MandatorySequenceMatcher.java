package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class MandatorySequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher delegate;

    public MandatorySequenceMatcher(SequenceMatcher delegate) {
        this.delegate = delegate;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = delegate.matches(sequenceMatcherRequest);

        if (!result.matched()) {
            return SequenceMatcherResult.error(sequenceMatcherRequest);
        }
        return result;
    }

    @Override
    public Explanation explain() {
        return new Explanation("mandatory", Collections.singletonList(delegate.explain()));
    }
}
