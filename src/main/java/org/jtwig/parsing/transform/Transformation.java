package org.jtwig.parsing.transform;

import org.jtwig.parsing.model.MatchResult;
import org.jtwig.parsing.model.tree.ContentNode;

public interface Transformation<T> {
    ContentNode<T> transform (MatchResult matchResult);
}
