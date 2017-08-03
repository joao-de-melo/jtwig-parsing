package org.jtwig.parsing.transform;

import com.google.common.base.Function;
import org.jtwig.parsing.tree.ContentNode;
import org.jtwig.parsing.tree.ListNode;
import org.jtwig.parsing.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class ListContentTransformation<T> implements Transformation<T> {
    private final Function<ListTransformationRequest, T> function;

    public ListContentTransformation(Function<ListTransformationRequest, T> function) {
        this.function = function;
    }

    @Override
    public ContentNode<T> transform(Node node) {
        if (node instanceof ListNode) {
            List<Node> nodes = ((ListNode) node).getNodes();
            List<Object> inputs = new ArrayList<>();

            for (Node subNode : nodes) {
                if (subNode instanceof ContentNode) {
                    inputs.add(((ContentNode) subNode).getContent());
                }
            }

            return new ContentNode<>(function.apply(new ListTransformationRequest(inputs)));
        } else {
            throw new IllegalArgumentException(String.format("Cannot transform when node is of type %s", node.getClass()));
        }
    }
}
