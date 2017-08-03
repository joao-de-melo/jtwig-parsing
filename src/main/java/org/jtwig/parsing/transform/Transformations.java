package org.jtwig.parsing.transform;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import org.jtwig.parsing.tree.ContentNode;
import org.jtwig.parsing.tree.Node;

public class Transformations {
    public static <T> Transformation<T> fromString(Function<String, T> transform) {
        return new FromStringTransformation<>(transform);
    }

    public static <T> Transformation<T> fromContentString(final Function<String, T> transform) {
        return new Transformation<T>() {
            @Override
            public ContentNode<T> transform(Node node) {
                if (node instanceof ContentNode) {
                    Object content = ((ContentNode) node).getContent();
                    if (content instanceof String) {
                        return new ContentNode<>(transform.apply((String) content));
                    } else {
                        throw new IllegalArgumentException(String.format("Expected String but got %s", content.getClass()));
                    }
                }
                throw new IllegalArgumentException(String.format("Expected ContentNode but got %s", node));
            }
        };
    }

    public static <T> Transformation<T> fromContentList(Function<ListTransformationRequest, T> transform) {
        return new ListContentTransformation<>(transform);
    }

    public static Transformation toContentString() {
        return new FromStringTransformation<>(Functions.<String>identity());
    }

    public static <T> Transformation<T> constant(final T value) {
        return new FromStringTransformation<>(new Function<String, T>() {
            @Override
            public T apply(String input) {
                return value;
            }
        });
    }

    public static <T> Transformation<T> as (Class<T> type) {
        return new Transformation<T>() {
            @Override
            public ContentNode<T> transform(Node node) {
                return (ContentNode<T>) node;
            }
        };
    }

    public static Transformation<Node> identity() {
        return new Transformation<Node>() {
            @Override
            public ContentNode<Node> transform(Node node) {
                return new ContentNode<>(node);
            }
        };
    }
}
