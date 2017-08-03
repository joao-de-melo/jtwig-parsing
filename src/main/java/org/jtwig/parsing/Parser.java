package org.jtwig.parsing;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jtwig.parsing.sequence.SequenceMatcher;
import org.jtwig.parsing.sequence.SequenceMatcherRequest;
import org.jtwig.parsing.sequence.SequenceMatcherResult;
import org.jtwig.parsing.transform.Transformation;

public class Parser {
    private final SequenceMatcher matcher;

    public Parser(SequenceMatcher matcher) {
        this.matcher = matcher;
    }

    public Result parse (String input) {
        return new Result(matcher.matches(new SequenceMatcherRequest(input.toCharArray(), 0)));
    }

    public static class Result {
        private final SequenceMatcherResult result;

        public Result(SequenceMatcherResult result) {
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

        public <T> T output (Transformation<T> transformation) {
            return transformation.transform(result.getNode().get()).getContent();
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
