package org.jtwig.parsing.character;

import static java.util.Arrays.asList;

public class CharacterMatchers {
    public static CharacterMatcher anyOf (String listOfChars) {
        return new AnyOfCharacterMatcher(listOfChars.toCharArray());
    }
    public static CharacterMatcher any () {
        return new AnyCharacterMatcher();
    }

    public static CharacterMatcher range (char start, char end) {
        return new CharacterRangeMatcher(start, end);
    }

    public static CharacterMatcher character (char c) {
        return new CharacterRangeMatcher(c, c);
    }

    public static CharacterMatcher noneOf (String listOfChars) {
        return new NoneOfCharacterMatcher(listOfChars.toCharArray());
    }

    public static CharacterMatcher endOfInput () {
        return new EndOfInputCharacterMatcher();
    }

    public static CharacterMatcher and (CharacterMatcher... list) {
        return new AndCharacterMatcher(asList(list));
    }

    public static CharacterMatcher or (CharacterMatcher... list) {
        return new OrCharacterMatcher(asList(list));
    }

    public static CharacterMatcher not (CharacterMatcher matcher) {
        return new NotCharacterMatcher(matcher);
    }

    public static CharacterMatcher whiteSpace () {
        return new WhiteSpaceCharacterMatcher();
    }
}
