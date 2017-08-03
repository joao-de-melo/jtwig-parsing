package org.jtwig.parsing.character;

import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class AnyCharacterMatcher implements CharacterMatcher {
    @Override
    public boolean matches(char character) {
        return true;
    }

    @Override
    public Explanation explain() {
        return new Explanation("any", Collections.<Explanation>emptyList());
    }
}
