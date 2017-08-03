package org.jtwig.parsing.integration.example.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SimpleTag<T> {
    private final boolean beforeTrim;
    private final boolean afterTrim;
    private final T content;

    public SimpleTag(boolean beforeTrim, boolean afterTrim, T content) {
        this.beforeTrim = beforeTrim;
        this.afterTrim = afterTrim;
        this.content = content;
    }

    public boolean isBeforeTrim() {
        return beforeTrim;
    }

    public boolean isAfterTrim() {
        return afterTrim;
    }

    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
