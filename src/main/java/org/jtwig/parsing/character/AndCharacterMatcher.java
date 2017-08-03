package org.jtwig.parsing.character;

import org.jtwig.parsing.explain.Explanation;

import java.util.ArrayList;
import java.util.List;

public class AndCharacterMatcher implements CharacterMatcher {
    private final List<CharacterMatcher> options;

    public AndCharacterMatcher(List<CharacterMatcher> options) {
        this.options = options;
    }

    @Override
    public boolean matches(char character) {
        for (CharacterMatcher option : options) {
            if (!option.matches(character)) return false;
        }

        return true;
    }

    @Override
    public Explanation explain() {
        ArrayList<Explanation> explanations = new ArrayList<>();
        for (CharacterMatcher option : options) {
            explanations.add(option.explain());
        }
        return new Explanation("and", explanations);
    }
}
