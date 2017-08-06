package org.jtwig.parsing.transform;

import com.google.common.base.Function;
import org.jtwig.parsing.model.MatchResult;
import org.jtwig.parsing.model.tree.ContentNode;

public class Transformations {
    public static <T> Transformation<T> fromString(Function<FromStringTransformation.Request, T> transform) {
        return new FromStringTransformation<>(transform);
    }

    public static <T> Transformation<T> fromContentString(final Function<String, T> transform) {
        return new Transformation<T>() {
            @Override
            public ContentNode<T> transform(MatchResult request) {
                if (request.getNode() instanceof ContentNode) {
                    Object content = ((ContentNode) request.getNode()).getContent();
                    if (content instanceof String) {
                        return new ContentNode<>(transform.apply((String) content));
                    } else {
                        throw new IllegalArgumentException(String.format("Expected String but got %s", content.getClass()));
                    }
                }
                throw new IllegalArgumentException(String.format("Expected ContentNode but got %s", request.getNode()));
            }
        };
    }

    public static <T> Transformation<T> fromContentList(Function<ListTransformationRequest, T> transform) {
        return new ListContentTransformation<>(transform);
    }

    public static Transformation<String> toContentString() {
        return new FromStringTransformation<>(new Function<FromStringTransformation.Request, String>() {
            @Override
            public String apply(FromStringTransformation.Request input) {
                return input.getInput();
            }
        });
    }

    public static <T> Transformation<T> constant(final T value) {
        return new Transformation<T>() {
            @Override
            public ContentNode<T> transform(MatchResult matchResult) {
                return new ContentNode<>(value);
            }
        };
    }

    public static <T> Transformation<T> as (final Class<T> type) {
        return new Transformation<T>() {
            @Override
            public ContentNode<T> transform(MatchResult request) {
                return new ContentNode<>(type.cast(((ContentNode) request.getNode()).getContent()));
            }
        };
    }
}
