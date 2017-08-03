package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;

import java.util.ArrayList;
import java.util.List;

public class FirstOfSequenceMatcher implements SequenceMatcher {
    private final List<SequenceMatcher> sequenceMatchers;

    public FirstOfSequenceMatcher(List<SequenceMatcher> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public Explanation explain() {
        List<Explanation> explanations = new ArrayList<>();
        for (SequenceMatcher matcher : sequenceMatchers) {
            explanations.add(matcher.explain());
        }
        return new Explanation("first of", explanations);
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
