package org.jtwig.parsing.integration.example.factories.expression;

import com.google.common.base.Function;
import org.jtwig.parsing.character.CharacterMatchers;
import org.jtwig.parsing.integration.example.factories.SequenceMatcherFactory;
import org.jtwig.parsing.sequence.SequenceMatcher;
import org.jtwig.parsing.transform.Transformations;

import static org.jtwig.parsing.sequence.SequenceMatchers.flatten;
import static org.jtwig.parsing.sequence.SequenceMatchers.match;
import static org.jtwig.parsing.sequence.SequenceMatchers.oneOrMore;
import static org.jtwig.parsing.sequence.SequenceMatchers.transform;

public class IntegerLiteralSequenceMatcherFactory implements SequenceMatcherFactory {
    @Override
    public SequenceMatcher create() {
        return transform(
                flatten(oneOrMore(match(CharacterMatchers.range('0', '9')))),
                Transformations.fromString(new Function<String, Integer>(){
                    @Override
                    public Integer apply(String input) {
                        return Integer.parseInt(input);
                    }
                })
        );
    }
}
