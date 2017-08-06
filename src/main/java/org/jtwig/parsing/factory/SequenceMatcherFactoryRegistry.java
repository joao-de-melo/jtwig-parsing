package org.jtwig.parsing.factory;

import org.jtwig.parsing.sequence.SequenceMatcher;

import java.util.ArrayList;
import java.util.List;

public class SequenceMatcherFactoryRegistry<I, O extends SequenceMatcher> {
    private final List<SequenceMatcherFactory<I, O>> factories;

    public SequenceMatcherFactoryRegistry(List<SequenceMatcherFactory<I, O>> factories) {
        this.factories = factories;
    }

    public List<O> create (I input) {
        List<O> result = new ArrayList<>();
        for (SequenceMatcherFactory<I, O> matcherFactory : factories) {
            result.add(matcherFactory.create(input));
        }
        return result;
    }
}
