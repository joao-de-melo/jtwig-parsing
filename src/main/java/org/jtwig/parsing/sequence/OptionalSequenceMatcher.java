package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class OptionalSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public OptionalSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.isMismatch()) return SequenceMatcherResult.match(0, sequenceMatcherRequest.text(0));

        return result;
    }

    @Override
    public Explanation explain() {
        return new Explanation("optional", Collections.singletonList(sequenceMatcher.explain()));
    }
}
