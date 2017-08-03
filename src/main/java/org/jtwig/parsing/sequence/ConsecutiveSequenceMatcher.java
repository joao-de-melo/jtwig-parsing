package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;
import org.jtwig.parsing.tree.ListNode;
import org.jtwig.parsing.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class ConsecutiveSequenceMatcher implements SequenceMatcher {
    private final List<SequenceMatcher> sequenceMatchers;

    public ConsecutiveSequenceMatcher(List<SequenceMatcher> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        int offset = 0;
        List<Node> nodes = new ArrayList<>();

        for (SequenceMatcher sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest.incrementOffset(offset));

            if (result.isError()) return result;
            if (!result.matched()) return SequenceMatcherResult.<ListNode>mismatch();

            offset += result.getJump();
            nodes.add(result.getNode().get());
        }

        return SequenceMatcherResult.match(offset, new ListNode(nodes));
    }

    @Override
    public Explanation explain() {
        List<Explanation> explanations = new ArrayList<>();

        for (SequenceMatcher sequenceMatcher : sequenceMatchers) {
            explanations.add(sequenceMatcher.explain());
        }
        return new Explanation(
                "sequence",
                explanations
        );
    }
}
