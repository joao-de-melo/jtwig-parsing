package org.jtwig.parsing.transform;

import org.jtwig.parsing.tree.ContentNode;
import org.jtwig.parsing.tree.Node;

public interface Transformation<T> {
    ContentNode<T> transform (Node node);
}
