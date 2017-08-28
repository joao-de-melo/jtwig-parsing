package org.jtwig.parsing.sequence;

public class TestSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public TestSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            return SequenceMatcherResult.match(0, sequenceMatcherRequest.text(0));
        }

        return result;
    }
}
