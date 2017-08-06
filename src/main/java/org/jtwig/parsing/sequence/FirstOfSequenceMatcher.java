package org.jtwig.parsing.sequence;

import java.util.List;

public class FirstOfSequenceMatcher implements SequenceMatcher {
    private final List<SequenceMatcher> sequenceMatchers;

    public FirstOfSequenceMatcher(List<SequenceMatcher> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        for (SequenceMatcher sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

            if (!result.isMismatch()) return result;
        }

        return SequenceMatcherResult.mismatch();
    }
}
