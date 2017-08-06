package org.jtwig.parsing.model.tree;

public class ContentNode<T> extends Node {
    private final T content;

    public ContentNode(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public boolean ofType (Class type) {
        return type.isInstance(content);
    }
}
