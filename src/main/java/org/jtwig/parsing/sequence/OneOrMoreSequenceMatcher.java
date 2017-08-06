package org.jtwig.parsing.sequence;

import org.jtwig.parsing.tree.ListNode;
import org.jtwig.parsing.tree.Node;

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

        int offset = 0;
        List<Node> nodes = new ArrayList<>(Collections.singleton(result.getMatchResult().get()));

        while (result.matched()) {
            nodes.add(result.getMatchResult().get());

            if (result.getJump() == 0) return SequenceMatcherResult.match(offset, new ListNode(nodes));
            offset += result.getJump();

            SequenceMatcherRequest newRequest = sequenceMatcherRequest.incrementOffset(offset);
            if (newRequest.isEndOfInput()) return SequenceMatcherResult.match(offset, new ListNode(nodes));

            result = sequenceMatcher.matches(newRequest);
        }

        if (result.isError()) return result;

        return SequenceMatcherResult.match(offset, new ListNode(nodes));
    }
}
