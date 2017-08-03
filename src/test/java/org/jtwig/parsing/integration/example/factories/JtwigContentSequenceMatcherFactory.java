package org.jtwig.parsing.integration.example.factories;

import org.jtwig.parsing.integration.example.config.ParserConfiguration;
import org.jtwig.parsing.sequence.SequenceMatcher;
import org.jtwig.parsing.sequence.SequenceMatchers;
import org.jtwig.parsing.transform.Transformations;

import static org.jtwig.parsing.sequence.SequenceMatchers.flatten;
import static org.jtwig.parsing.sequence.SequenceMatchers.transform;

public class JtwigContentSequenceMatcherFactory implements SequenceMatcherFactory {
    private final ParserConfiguration parserConfiguration;

    public JtwigContentSequenceMatcherFactory(ParserConfiguration parserConfiguration) {
        this.parserConfiguration = parserConfiguration;
    }

    @Override
    public SequenceMatcher create () {
        return transform(flatten(SequenceMatchers.zeroOrMore(
                SequenceMatchers.not(SequenceMatchers.or(
                        SequenceMatchers.string(parserConfiguration.getStartCode()),
                        SequenceMatchers.string(parserConfiguration.getStartComment()),
                        SequenceMatchers.string(parserConfiguration.getStartOutput())
                ))
        )), Transformations.toContentString());
    }
}
