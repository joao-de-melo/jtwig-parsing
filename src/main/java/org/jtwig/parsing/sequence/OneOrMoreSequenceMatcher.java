package org.jtwig.parsing.sequence;

import org.jtwig.parsing.model.MatchResult;
import org.jtwig.parsing.model.tree.ListNode;
import org.jtwig.parsing.model.tree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OneOrMoreSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public OneOrMoreSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);
        if (!result.matched()) return result;

        int jump = 0;
        List<Node> nodes = new ArrayList<>(Collections.singleton(result.getMatchResult().getNode()));

        while (result.matched()) {
            nodes.add(result.getMatchResult().getNode());

            if (result.getJump() == 0) return SequenceMatcherResult.match(jump, result(sequenceMatcherRequest, jump, nodes));
            jump += result.getJump();

            SequenceMatcherRequest newRequest = sequenceMatcherRequest.incrementOffset(jump);
            if (newRequest.isEndOfInput()) return SequenceMatcherResult.match(jump, result(sequenceMatcherRequest, jump, nodes));

            result = sequenceMatcher.matches(newRequest);
        }

        if (result.isError()) return result;

        return SequenceMatcherResult.match(jump, result(sequenceMatcherRequest, jump, nodes));
    }

    private MatchResult result(SequenceMatcherRequest sequenceMatcherRequest, int jump, List<Node> nodes) {
        return new MatchResult(sequenceMatcherRequest.range(jump), new ListNode(nodes));
    }
}
