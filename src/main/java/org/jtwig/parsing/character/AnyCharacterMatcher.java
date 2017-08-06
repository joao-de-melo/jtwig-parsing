package org.jtwig.parsing.character;

public class AnyCharacterMatcher implements CharacterMatcher {
    @Override
    public boolean matches(char character) {
        return true;
    }
}
