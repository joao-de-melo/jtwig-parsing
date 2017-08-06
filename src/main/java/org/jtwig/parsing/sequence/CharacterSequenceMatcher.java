package org.jtwig.parsing.sequence;

import org.jtwig.parsing.character.CharacterMatcher;

public class CharacterSequenceMatcher implements SequenceMatcher {
    private final CharacterMatcher matcher;

    public CharacterSequenceMatcher(CharacterMatcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        if (matcher.matches(sequenceMatcherRequest.getCurrentCharacter())) {
            return SequenceMatcherResult.match(1, sequenceMatcherRequest.text(1));
        }


        return SequenceMatcherResult.mismatch();
    }
}
