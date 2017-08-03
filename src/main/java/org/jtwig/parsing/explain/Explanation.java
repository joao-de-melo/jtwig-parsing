package org.jtwig.parsing.explain;

import java.util.List;

public class Explanation {
    private final String explanation;
    private final List<Explanation> explanations;

    public Explanation(String explanation, List<Explanation> explanations) {
        this.explanation = explanation;
        this.explanations = explanations;
    }

    public String getExplanation() {
        return explanation;
    }

    public List<Explanation> getExplanations() {
        return explanations;
    }
}
