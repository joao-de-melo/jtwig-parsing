package org.jtwig.parsing.sequence;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jtwig.parsing.explain.Explanation;

import java.util.Collections;

public class StringSequenceMatcher implements SequenceMatcher {
    private final String string;

    public StringSequenceMatcher(String string) {
        this.string = string;
    }

    @Override
    public Explanation explain() {
        return new Explanation(String.format("\"%s\"", StringEscapeUtils.escapeJava(string)), Collections.<Explanation>emptyList());
    }

    @Override
    public SequenceMatcherResult matches(SequenceMatcherRequest sequenceMatcherRequest) {
        if (matchesString(sequenceMatcherRequest)) {
            return SequenceMatcherResult.match(string.length(), sequenceMatcherRequest.text(string.length()));
        }

        return SequenceMatcherResult.mismatch();
    }

    private boolean matchesString(SequenceMatcherRequest sequenceMatcherRequest) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (sequenceMatcherRequest.getCharacter(i) != chars[i])
                return false;
        }

        return true;
    }
}
