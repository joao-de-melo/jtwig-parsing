package org.jtwig.parsing.integration.example.factories;

import com.google.common.base.Function;
import org.jtwig.parsing.integration.example.config.ParserConfiguration;
import org.jtwig.parsing.integration.example.model.SimpleTag;
import org.jtwig.parsing.sequence.MandatorySequenceMatcher;
import org.jtwig.parsing.sequence.SequenceMatcher;
import org.jtwig.parsing.transform.ListTransformationRequest;
import org.jtwig.parsing.transform.Transformations;

import static org.jtwig.parsing.character.CharacterMatchers.character;
import static org.jtwig.parsing.sequence.SequenceMatchers.match;
import static org.jtwig.parsing.sequence.SequenceMatchers.optional;
import static org.jtwig.parsing.sequence.SequenceMatchers.sequence;
import static org.jtwig.parsing.sequence.SequenceMatchers.skipWhitespaces;
import static org.jtwig.parsing.sequence.SequenceMatchers.string;
import static org.jtwig.parsing.sequence.SequenceMatchers.transform;
import static org.jtwig.parsing.transform.Transformations.fromString;

public class JtwigCodeSequenceMatcherFactory implements SequenceMatcherFactory {
    private final ParserConfiguration configuration;
    private final SequenceMatcherFactory factory;

    public JtwigCodeSequenceMatcherFactory(ParserConfiguration configuration, SequenceMatcherFactory factory) {
        this.configuration = configuration;
        this.factory = factory;
    }

    @Override
    public SequenceMatcher create() {
        return transform(
                sequence(
                        string(configuration.getStartCode()),
                        transform(skipWhitespaces(optional(match(character('-')))), fromString(isTrimOperator())),
                        factory.create(),
                        transform(skipWhitespaces(optional(match(character('-')))), fromString(isTrimOperator())),

                        mandatory(string(configuration.getEndCode()))
                ),
                Transformations.fromContentList(new Function<ListTransformationRequest, SimpleTag>() {
                    @Override
                    public SimpleTag apply(ListTransformationRequest input) {
                        return new SimpleTag(input.get(0, Boolean.class), input.get(2, Boolean.class), input.get(1, Object.class));
                    }
                })
        );
    }

    private SequenceMatcher mandatory(SequenceMatcher matcher) {
        return new MandatorySequenceMatcher(matcher);
    }

    private Function<String, Boolean> isTrimOperator() {
        return new Function<String, Boolean>() {
            @Override
            public Boolean apply(String input) {
                return "-".equals(input);
            }
        };
    }
}
