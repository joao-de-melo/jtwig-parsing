package org.jtwig.parsing.sequence;

import org.jtwig.parsing.tree.ListNode;
import org.jtwig.parsing.tree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlattenListSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher matcher;
    private final int depth;

    public FlattenListSequenceMatcher(SequenceMatcher matcher, int depth) {
        this.matcher = matcher;
        this.depth = depth;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = matcher.matches(sequenceMatcherRequest);

        if (result.matched()) {
            Node node = result.getMatchResult().get();
            return SequenceMatcherResult.match(result.getJump(), new ListNode(flatten(node, depth)));
        }
        return result;
    }

    private List<Node> flatten(Node node, int depth) {
        if (depth == 0) return Collections.singletonList(node);

        List<Node> result = new ArrayList<>();
        if (node instanceof ListNode) {
            List<Node> nodes = ((ListNode) node).getNodes();
            for (Node subNode : nodes) {
                result.addAll(flatten(subNode, depth - 1));
            }
        } else {
            result.add(node);
        }

        return result;
    }
}
