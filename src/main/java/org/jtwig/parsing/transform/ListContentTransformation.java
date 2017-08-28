package org.jtwig.parsing.transform;

import com.google.common.base.Function;
import org.jtwig.parsing.model.MatchResult;
import org.jtwig.parsing.model.tree.ContentNode;
import org.jtwig.parsing.model.tree.ListNode;
import org.jtwig.parsing.model.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class ListContentTransformation<T> implements Transformation<T> {
    private final Function<Request, T> function;

    public ListContentTransformation(Function<Request, T> function) {
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

            return new ContentNode<>(function.apply(new Request(request, inputs)));
        } else {
            throw new IllegalArgumentException(String.format("Cannot transform when node is of type %s", request.getNode().getClass()));
        }
    }

    public static class Request {
        private final MatchResult matchResult;
        private final List<Object> values;

        public Request(MatchResult matchResult, List<Object> values) {
            this.matchResult = matchResult;
            this.values = values;
        }

        public <T> T get (int index, Class<T> type) {
            return type.cast(values.get(index));
        }

        public int size () {
            return values.size();
        }

        public List<Object> getValues() {
            return values;
        }

        public MatchResult getMatchResult() {
            return matchResult;
        }
    }

}

