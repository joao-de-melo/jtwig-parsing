package org.jtwig.parsing.sequence;

import org.jtwig.parsing.model.MatchResult;
import org.jtwig.parsing.model.tree.ListNode;
import org.jtwig.parsing.model.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class MergeSequenceMatcher implements SequenceMatcher {
    private final List<SequenceMatcher> sequenceMatchers;

    public MergeSequenceMatcher(List<SequenceMatcher> sequenceMatchers) {
        this.sequenceMatchers = sequenceMatchers;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        int jump = 0;
        List<Node> nodes = new ArrayList<>();

        for (SequenceMatcher sequenceMatcher : sequenceMatchers) {
            SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest.incrementOffset(jump));

            if (result.isError()) return result;
            if (!result.matched()) return SequenceMatcherResult.<ListNode>mismatch();

            jump += result.getJump();

            if (result.getMatchResult().getNode() instanceof ListNode) {
                nodes.addAll(((ListNode) result.getMatchResult().getNode()).getNodes());
            } else {
                nodes.add(result.getMatchResult().getNode());
            }
        }

        return SequenceMatcherResult.match(jump, new MatchResult(sequenceMatcherRequest.range(jump), new ListNode(nodes)));
    }
}
