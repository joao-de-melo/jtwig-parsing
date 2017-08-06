package org.jtwig.parsing.character;

import org.apache.commons.lang3.StringUtils;

public class WhiteSpaceCharacterMatcher implements CharacterMatcher {
    @Override
    public boolean matches(char character) {
        return StringUtils.isBlank(String.valueOf(character));
    }
}
