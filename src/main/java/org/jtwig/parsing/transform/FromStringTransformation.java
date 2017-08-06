package org.jtwig.parsing.transform;

import com.google.common.base.Function;
import org.jtwig.parsing.model.MatchResult;
import org.jtwig.parsing.model.tree.ContentNode;
import org.jtwig.parsing.model.tree.TextNode;

public class FromStringTransformation<T> implements Transformation<T> {
    private final Function<Request, T> function;

    public FromStringTransformation(Function<Request, T> function) {
        this.function = function;
    }

    @Override
    public ContentNode<T> transform(MatchResult request) {
        if (request.getNode() instanceof TextNode) {
            return new ContentNode<>(function.apply(new Request(((TextNode) request.getNode()).getText(), request)));
        } else {
            throw new IllegalArgumentException(String.format("Cannot apply string transformation on input which is not a String but %s", request.getNode().getClass()));
        }
    }

    public static class Request {
        private final String input;
        private final MatchResult result;

        public Request(String input, MatchResult result) {
            this.input = input;
            this.result = result;
        }

        public String getInput() {
            return input;
        }

        public MatchResult getResult() {
            return result;
        }
    }
}
