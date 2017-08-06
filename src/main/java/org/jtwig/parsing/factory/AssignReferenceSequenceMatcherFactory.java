package org.jtwig.parsing.factory;

import org.jtwig.parsing.sequence.SequenceMatcher;

import java.util.concurrent.atomic.AtomicReference;

public class AssignReferenceSequenceMatcherFactory<I, O extends SequenceMatcher> implements SequenceMatcherFactory<I, O> {
    public static <I, O extends SequenceMatcher> SequenceMatcherFactory<I, O> assignReference (AtomicReference<O> reference, SequenceMatcherFactory<I, O> factory) {
        return new AssignReferenceSequenceMatcherFactory<>(factory, reference);
    }

    private final SequenceMatcherFactory<I, O> sequenceMatcherFactory;
    private final AtomicReference<O> reference;

    public AssignReferenceSequenceMatcherFactory(SequenceMatcherFactory<I, O> sequenceMatcherFactory, AtomicReference<O> reference) {
        this.sequenceMatcherFactory = sequenceMatcherFactory;
        this.reference = reference;
    }

    @Override
    public O create(I input) {
        reference.set(sequenceMatcherFactory.create(input));
        return reference.get();
    }
}
