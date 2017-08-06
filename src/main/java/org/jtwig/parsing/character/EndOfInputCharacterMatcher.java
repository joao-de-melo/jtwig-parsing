package org.jtwig.parsing.character;

public class EndOfInputCharacterMatcher implements CharacterMatcher {
    public static final char EOI = (char) -1;

    @Override
    public boolean matches(char character) {
        return character == EOI;
    }
}
