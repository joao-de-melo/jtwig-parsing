package org.jtwig.parsing.character;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class CharacterRangeMatcher implements CharacterMatcher {
    private final char start;
    private final char end;

    public CharacterRangeMatcher(char start, char end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean matches(char character) {
        return character >= start && character <= end;
    }

    @Override
    public Explanation explain() {
        if (start == end) {
            return new Explanation(String.format("'%s'", StringEscapeUtils.escapeJava(String.valueOf(start))), Collections.<Explanation>emptyList());
        }
        return new Explanation(String.format("[%s-%s]", StringEscapeUtils.escapeJava(String.valueOf(start)), StringEscapeUtils.escapeJava(String.valueOf(end))), Collections.<Explanation>emptyList());
    }
}
