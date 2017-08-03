package org.jtwig.parsing.character;

import org.apache.commons.lang3.StringUtils;
import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class WhiteSpaceCharacterMatcher implements CharacterMatcher {
    @Override
    public Explanation explain() {
        return new Explanation("white space", Collections.<Explanation>emptyList());
    }

    @Override
    public boolean matches(char character) {
        return StringUtils.isBlank(String.valueOf(character));
    }
}
