package org.jtwig.parsing.transform;

import com.google.common.base.Function;
import org.jtwig.parsing.model.MatchResult;
import org.jtwig.parsing.model.tree.ContentNode;
import org.jtwig.parsing.model.tree.ListNode;
import org.jtwig.parsing.model.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class ListContentTransformation<T> implements Transformation<T> {
    private final Function<ListTransformationRequest, T> function;

    public ListContentTransformation(Function<ListTransformationRequest, T> function) {
        this.function = function;
    }

    @Override
    public ContentNode<T> transform(MatchResult request) {
        if (request.getNode() instanceof ListNode) {
            List<Node> nodes = ((ListNode) request.getNode()).getNodes();
            List<Object> inputs = new ArrayList<>();

            for (Node subNode : nodes) {
                if (subNode instanceof ContentNode) {
                    inputs.add(((ContentNode) subNode).getContent());
                }
            }

            return new ContentNode<>(function.apply(new ListTransformationRequest(request, inputs)));
        } else {
            throw new IllegalArgumentException(String.format("Cannot transform when node is of type %s", request.getNode().getClass()));
        }
    }
}
