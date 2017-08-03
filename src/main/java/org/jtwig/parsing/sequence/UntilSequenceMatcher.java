package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class UntilSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public UntilSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public Explanation explain() {
        return new Explanation("until", Collections.singletonList(sequenceMatcher.explain()));
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherRequest request = sequenceMatcherRequest;
        SequenceMatcherResult result = sequenceMatcher.matches(request);

        int jump = 0;

        while (result.isMismatch() && !request.isEndOfInput()) {
            request = request.incrementOffset(1);
            result = sequenceMatcher.matches(request);
            jump++;
        }

        if (result.matched() || request.isEndOfInput()) {
            return SequenceMatcherResult.match(jump, request.text(jump));
        } else {
            return result;
        }
    }
}
