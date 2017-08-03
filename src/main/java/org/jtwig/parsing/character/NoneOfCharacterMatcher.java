package org.jtwig.parsing.character;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class NoneOfCharacterMatcher implements CharacterMatcher {
    private final char[] possibilities;

    public NoneOfCharacterMatcher(char[] possibilities) {
        this.possibilities = possibilities;
    }

    @Override
    public boolean matches(char character) {
        for (char possibility : possibilities) {
            if (possibility == character)
                return false;
        }

        return true;
    }

    @Override
    public Explanation explain() {
        return new Explanation(String.format("[^%s]", StringEscapeUtils.escapeJava(new String(possibilities))), Collections.<Explanation>emptyList());
    }
}
