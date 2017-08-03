package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class NotSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public NotSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);
        
        switch (result.getType()) {
            case ERROR:
                return result;
            case MATCHED:
                return SequenceMatcherResult.mismatch();
            case MISMATCH:
            default:
                return SequenceMatcherResult.match(1, sequenceMatcherRequest.text(1));
        }
    }

    @Override
    public Explanation explain() {
        return new Explanation("not", Collections.singletonList(sequenceMatcher.explain()));
    }
}
