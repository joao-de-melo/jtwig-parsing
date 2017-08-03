package org.jtwig.parsing.character;

import org.jtwig.parsing.explain.SelfExplained;

public interface CharacterMatcher extends SelfExplained {
    boolean matches (char character);
}
