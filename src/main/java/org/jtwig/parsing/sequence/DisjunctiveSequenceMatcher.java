package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;

import java.util.ArrayList;
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

    @Override
    public Explanation explain() {
        ArrayList<Explanation> explanations = new ArrayList<>();
        for (SequenceMatcher sequenceMatcher : sequenceMatcherList) {
            explanations.add(sequenceMatcher.explain());
        }
        return new Explanation("alternatives", explanations);
    }
}
