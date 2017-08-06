package org.jtwig.parsing.transform;

import org.jtwig.parsing.model.MatchResult;

import java.util.List;

public class ListTransformationRequest {
    private final MatchResult matchResult;
    private final List<Object> values;

    public ListTransformationRequest(MatchResult matchResult, List<Object> values) {
        this.matchResult = matchResult;
        this.values = values;
    }

    public <T> T get (int index, Class<T> type) {
        return type.cast(values.get(index));
    }

    public int size () {
        return values.size();
    }

    public List<Object> getValues() {
        return values;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }
}
