package org.jtwig.parsing.character;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class AnyOfCharacterMatcher implements CharacterMatcher {
    private final char[] possibilities;

    public AnyOfCharacterMatcher(char[] possibilities) {
        this.possibilities = possibilities;
    }

    @Override
    public boolean matches(char character) {
        for (char possibility : possibilities) {
            if (possibility == character)
                return true;
        }

        return false;
    }

    @Override
    public Explanation explain() {
        return new Explanation(String.format("[%s]", StringEscapeUtils.escapeJava(new String(possibilities))), Collections.<Explanation>emptyList());
    }
}
