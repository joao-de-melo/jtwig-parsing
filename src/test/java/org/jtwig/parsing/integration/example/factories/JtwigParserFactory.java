package org.jtwig.parsing.integration.example.factories;

import org.jtwig.parsing.sequence.SequenceMatcher;

import static org.jtwig.parsing.sequence.SequenceMatchers.flattenList;
import static org.jtwig.parsing.sequence.SequenceMatchers.sequence;
import static org.jtwig.parsing.sequence.SequenceMatchers.zeroOrMore;

public class JtwigParserFactory implements SequenceMatcherFactory {
    private final JtwigCodeSequenceMatcherFactory jtwigCodeSequenceMatcherFactory;
    private final JtwigContentSequenceMatcherFactory jtwigContentSequenceMatcherFactory;

    public JtwigParserFactory(JtwigCodeSequenceMatcherFactory jtwigCodeSequenceMatcherFactory, JtwigContentSequenceMatcherFactory jtwigContentSequenceMatcherFactory) {
        this.jtwigCodeSequenceMatcherFactory = jtwigCodeSequenceMatcherFactory;
        this.jtwigContentSequenceMatcherFactory = jtwigContentSequenceMatcherFactory;
    }

    @Override
    public SequenceMatcher create() {
        return flattenList(
                zeroOrMore(
                        sequence(
                                jtwigContentSequenceMatcherFactory.create(),
                                jtwigCodeSequenceMatcherFactory.create()
                        )
                ),
                2
        );
    }
}
