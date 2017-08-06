package org.jtwig.parsing.model.tree;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class Node {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
