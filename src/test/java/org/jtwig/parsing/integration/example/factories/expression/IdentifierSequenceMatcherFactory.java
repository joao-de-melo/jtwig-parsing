package org.jtwig.parsing.integration.example.factories.expression;

import org.jtwig.parsing.character.CharacterMatchers;
import org.jtwig.parsing.integration.example.factories.SequenceMatcherFactory;
import org.jtwig.parsing.sequence.SequenceMatcher;
import org.jtwig.parsing.transform.Transformations;

import static org.jtwig.parsing.sequence.SequenceMatchers.flatten;
import static org.jtwig.parsing.sequence.SequenceMatchers.match;
import static org.jtwig.parsing.sequence.SequenceMatchers.oneOrMore;
import static org.jtwig.parsing.sequence.SequenceMatchers.transform;

public class IdentifierSequenceMatcherFactory implements SequenceMatcherFactory {
    @Override
    public SequenceMatcher create() {
        return transform(
                flatten(oneOrMore(match(CharacterMatchers.range('a', 'z')))),
                Transformations.toContentString()
        );
    }
}
