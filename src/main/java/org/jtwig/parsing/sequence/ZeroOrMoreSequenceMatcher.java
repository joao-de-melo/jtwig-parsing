package org.jtwig.parsing.sequence;

import org.jtwig.parsing.explain.Explanation;
import org.jtwig.parsing.tree.ListNode;
import org.jtwig.parsing.tree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZeroOrMoreSequenceMatcher implements SequenceMatcher {
    private final SequenceMatcher sequenceMatcher;

    public ZeroOrMoreSequenceMatcher(SequenceMatcher sequenceMatcher) {
        this.sequenceMatcher = sequenceMatcher;
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        int jump = 0;
        SequenceMatcherResult result = sequenceMatcher.matches(sequenceMatcherRequest);
        List<Node> nodes = new ArrayList<>();

        while (result.matched()) {
            nodes.add(result.getNode().get());
            if (result.getJump() == 0) return SequenceMatcherResult.match(jump, new ListNode(nodes));

            jump += result.getJump();
            SequenceMatcherRequest newRequest = sequenceMatcherRequest.incrementOffset(jump);

            if (newRequest.isEndOfInput()) return SequenceMatcherResult.match(jump, new ListNode(nodes));
            result = sequenceMatcher.matches(newRequest);
        }

        if (result.isError()) return result;

        return SequenceMatcherResult.match(jump, new ListNode(nodes));
    }

    @Override
    public Explanation explain() {
        return new Explanation("zero or more", Collections.singletonList(sequenceMatcher.explain()));
    }
}
