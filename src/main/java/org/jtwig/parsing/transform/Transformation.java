package org.jtwig.parsing.transform;

import org.jtwig.parsing.sequence.MatchResult;

public interface Transformation {
    MatchResult transform (MatchResult request);
}
