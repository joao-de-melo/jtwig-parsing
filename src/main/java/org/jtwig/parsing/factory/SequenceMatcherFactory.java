package org.jtwig.parsing.factory;

import org.jtwig.parsing.sequence.SequenceMatcher;

public interface SequenceMatcherFactory<I, O extends SequenceMatcher> {
    O create (I input);
}
