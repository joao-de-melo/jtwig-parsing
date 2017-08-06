package org.jtwig.parsing.sequence;

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
}
