package org.jtwig.parsing.factory;

import org.jtwig.parsing.sequence.SequenceMatcher;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceSequenceMatcherFactory<I, O extends SequenceMatcher> implements SequenceMatcherFactory<I, O> {
    public static <I, O extends SequenceMatcher> SequenceMatcherFactory<I, O>  returnReference (AtomicReference<O> reference) {
        return new ReferenceSequenceMatcherFactory<>(reference);
    }

    private final AtomicReference<O> reference;

    public ReferenceSequenceMatcherFactory(AtomicReference<O> reference) {
        this.reference = reference;
    }

    @Override
    public O create(I input) {
        return reference.get();
    }
}
