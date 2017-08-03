package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.SelfExplained;

public interface SequenceMatcher extends SelfExplained {
    SequenceMatcherResult matches (SequenceMatcherRequest sequenceMatcherRequest);
}
