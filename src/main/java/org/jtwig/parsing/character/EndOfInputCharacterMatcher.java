package org.jtwig.parsing.character;

import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class EndOfInputCharacterMatcher implements CharacterMatcher {
    public static final char EOI = (char) -1;

    @Override
    public boolean matches(char character) {
        return character == EOI;
    }

    @Override
    public Explanation explain() {
        return new Explanation("EOF", Collections.<Explanation>emptyList());
    }
}
