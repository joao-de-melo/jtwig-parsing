package org.jtwig.parsing.character;

import org.jtwig.parsing.explain.Explanation;

import java.util.ArrayList;
import java.util.List;

public class OrCharacterMatcher implements CharacterMatcher {
    private final List<CharacterMatcher> options;

    public OrCharacterMatcher(List<CharacterMatcher> options) {
        this.options = options;
    }

    @Override
    public boolean matches(char character) {
        for (CharacterMatcher option : options) {
            if (option.matches(character)) return true;
        }

        return false;
    }

    @Override
    public Explanation explain() {
        ArrayList<Explanation> explanations = new ArrayList<>();
        for (CharacterMatcher option : options) {
            explanations.add(option.explain());
        }
        return new Explanation("or", explanations);
    }
}
