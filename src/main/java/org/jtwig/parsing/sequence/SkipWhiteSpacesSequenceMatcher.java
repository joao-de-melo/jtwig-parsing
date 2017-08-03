package org.jtwig.parsing.sequence;

import org.jtwig.parsing.character.WhiteSpaceCharacterMatcher;
import org.jtwig.parsing.explain.Explanation;

public class SkipWhiteSpacesSequenceMatcher implements SequenceMatcher {
    private final WhiteSpaceCharacterMatcher whiteSpaceCharacterMatcher;
    private final SequenceMatcher sequenceMatcher;

    public SkipWhiteSpacesSequenceMatcher(WhiteSpaceCharacterMatcher whiteSpaceCharacterMatcher, SequenceMatcher sequenceMatcher) {
        this.whiteSpaceCharacterMatcher = whiteSpaceCharacterMatcher;
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public Explanation explain() {
        return sequenceMatcher.explain();
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        int beforeJump = skipWhiteSpaces(sequenceMatcherRequest);
        SequenceMatcherRequest request = sequenceMatcherRequest.incrementOffset(beforeJump);
        SequenceMatcherResult result = sequenceMatcher.matches(request);
        int afterJump = skipWhiteSpaces(request.incrementOffset(result.getJump()));
        return result.withJump(beforeJump+result.getJump()+afterJump);
    }

    private int skipWhiteSpaces(SequenceMatcherRequest sequenceMatcherRequest) {
        int jump = 0;

        while (whiteSpaceCharacterMatcher.matches(sequenceMatcherRequest.getCharacter(jump))) {
            jump++;
        }

        return jump;
    }
}
