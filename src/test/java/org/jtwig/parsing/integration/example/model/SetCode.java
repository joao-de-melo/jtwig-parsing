package org.jtwig.parsing.integration.example.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SetCode {
    private final String variable;
    private final Integer value;

    public SetCode(String variable, Integer value) {
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
