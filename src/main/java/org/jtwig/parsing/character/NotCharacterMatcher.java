package org.jtwig.parsing.character;

import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class NotCharacterMatcher implements CharacterMatcher {
    private final CharacterMatcher delegate;

    public NotCharacterMatcher(CharacterMatcher delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean matches(char character) {
        return !delegate.matches(character);
    }

    @Override
    public Explanation explain() {
        return new Explanation("not", Collections.singletonList(delegate.explain()));
    }
}
