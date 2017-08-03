package org.jtwig.parsing.explain;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;
import java.util.Collections;

public class ExplainationPrinter {
    public void print (Explanation explanation, PrintStream outputStream) {
        print(explanation, outputStream, 0);
    }

    private void print(Explanation explanation, PrintStream outputStream, int offset) {
        outputStream.print(StringUtils.join(Collections.nCopies(offset, " "), ""));
        outputStream.print(" - ");
        outputStream.println(explanation.getExplanation());

        for (Explanation sub : explanation.getExplanations()) {
            print(sub, outputStream, increaseOffset(offset));
        }
    }

    private int increaseOffset(int offset) {
        return offset + 2;
    }
}
