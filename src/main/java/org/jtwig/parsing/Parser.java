package org.jtwig.parsing;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jtwig.parsing.model.tree.ContentNode;
import org.jtwig.parsing.sequence.SequenceMatcherRequest;
import org.jtwig.parsing.sequence.SequenceMatcherResult;
import org.jtwig.parsing.sequence.TransformSequenceMatcher;

public class Parser<T> {
    private final Class<T> type;
    private final TransformSequenceMatcher<T> matcher;

    public Parser(Class<T> type, TransformSequenceMatcher<T> matcher) {
        this.type = type;
        this.matcher = matcher;
    }

    public Result<T> parse (String input) {
        return new Result<>(type, matcher.matches(new SequenceMatcherRequest(input.toCharArray(), 0)));
    }

    public static class Result<T> {
        private final Class<T> type;
        private final SequenceMatcherResult result;

        public Result(Class<T> type, SequenceMatcherResult result) {
            this.type = type;
            this.result = result;
        }

        public boolean isError () {
            return result.isError();
        }

        public int getOffset () {
            return result.getJump();
        }

        public boolean isSuccess () {
            return result.matched();
        }

        public  T output () {
            return type.cast(((ContentNode) result.getMatchResult().getNode()).getContent());
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
