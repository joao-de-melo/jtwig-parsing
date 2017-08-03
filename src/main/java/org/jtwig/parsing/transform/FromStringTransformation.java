package org.jtwig.parsing.transform;

import com.google.common.base.Function;
import org.jtwig.parsing.tree.ContentNode;
import org.jtwig.parsing.tree.Node;
import org.jtwig.parsing.tree.TextNode;

public class FromStringTransformation<T> implements Transformation<T> {
    private final Function<String, T> function;

    public FromStringTransformation(Function<String, T> function) {
        this.function = function;
    }

    @Override
    public ContentNode<T> transform(Node node) {
        if (node instanceof TextNode) {
            return new ContentNode<>(function.apply(((TextNode) node).getText()));
        } else {
            throw new IllegalArgumentException(String.format("Cannot apply string transformation on input which is not a String but %s", node.getClass()));
        }
    }
}
