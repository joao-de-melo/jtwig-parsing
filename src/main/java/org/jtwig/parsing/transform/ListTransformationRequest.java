package org.jtwig.parsing.transform;

import java.util.List;

public class ListTransformationRequest {
    private final List<Object> values;

    public ListTransformationRequest(List<Object> values) {
        this.values = values;
    }

    public <T> T get (int index, Class<T> type) {
        return type.cast(values.get(index));
    }

    public int size () {
        return values.size();
    }
}
