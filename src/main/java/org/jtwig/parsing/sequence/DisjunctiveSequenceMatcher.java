package org.jtwig.parsing.sequence;

import java.util.List;

public class DisjunctiveSequenceMatcher implements SequenceMatcher {
    private final List<SequenceMatcher> sequenceMatcherList;

    public DisjunctiveSequenceMatcher(List<SequenceMatcher> sequenceMatcherList) {
        this.sequenceMatcherList = sequenceMatcherList;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        for (SequenceMatcher matcher : sequenceMatcherList) {
            SequenceMatcherResult result = matcher.matches(sequenceMatcherRequest);

            if (result.isError()) return result;
            if (result.matched()) return result;
        }

        return SequenceMatcherResult.mismatch();
    }
}
