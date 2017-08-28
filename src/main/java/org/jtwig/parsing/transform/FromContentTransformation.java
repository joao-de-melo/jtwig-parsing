package org.jtwig.parsing.transform;

import com.google.common.base.Function;
import org.jtwig.parsing.model.MatchResult;
import org.jtwig.parsing.model.tree.ContentNode;

public class FromContentTransformation<I, T> implements Transformation<T> {
    private final Function<Request<I>, T> function;

    public FromContentTransformation(Function<Request<I>, T> function) {
        this.function = function;
    }

    @Override
    public ContentNode<T> transform(MatchResult matchResult) {
        return new ContentNode<>(function.apply(new Request<I>(
                matchResult,
                ((ContentNode<I>)matchResult.getNode()).getContent()
        )));
    }

    public static class Request<V> {
        private final MatchResult matchResult;
        private final V value;

        public Request(MatchResult matchResult, V value) {
            this.matchResult = matchResult;
            this.value = value;
        }

        public MatchResult getMatchResult() {
            return matchResult;
        }

        public V getValue() {
            return value;
        }
    }
}
