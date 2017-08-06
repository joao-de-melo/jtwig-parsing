package org.jtwig.parsing.transform;

public class TransformationRequest<T> {
    private final TransformationContext context;
    private final T value;

    public TransformationRequest(TransformationContext context, T value) {
        this.context = context;
        this.value = value;
    }

    public TransformationContext getContext() {
        return context;
    }

    public T getValue() {
        return value;
    }
}
