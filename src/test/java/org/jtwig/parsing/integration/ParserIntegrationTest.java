package org.jtwig.parsing.integration;

import com.google.common.base.Function;
import org.jtwig.parsing.Parser;
import org.jtwig.parsing.sequence.SequenceMatcher;
import org.jtwig.parsing.transform.ListTransformationRequest;
import org.jtwig.parsing.transform.Transformation;
import org.jtwig.parsing.transform.Transformations;
import org.junit.Test;

import static org.jtwig.parsing.character.CharacterMatchers.any;
import static org.jtwig.parsing.sequence.SequenceMatchers.flatten;
import static org.jtwig.parsing.sequence.SequenceMatchers.match;
import static org.jtwig.parsing.sequence.SequenceMatchers.not;
import static org.jtwig.parsing.sequence.SequenceMatchers.or;
import static org.jtwig.parsing.sequence.SequenceMatchers.sequence;
import static org.jtwig.parsing.sequence.SequenceMatchers.string;
import static org.jtwig.parsing.sequence.SequenceMatchers.transform;
import static org.jtwig.parsing.sequence.SequenceMatchers.zeroOrMore;
import static org.jtwig.parsing.transform.Transformations.toContentString;
import static org.junit.Assert.assertEquals;

public class ParserIntegrationTest {
    @Test
    public void simpleTest() throws Exception {
        // given
        String input = "test {{ 123";
        SequenceMatcher grammar = sequence(
                transform(flatten(zeroOrMore(not(or(
                        string("{{"),
                        string("{%"),
                        string("{#")
                )))), toContentString()),
                or(
                        transform(string("{{"), Transformations.constant(TestCode.OUTPUT)),
                        transform(string("{%"), Transformations.constant(TestCode.CODE)),
                        transform(string("{#"), Transformations.constant(TestCode.COMMENT))
                ),
                transform(
                        flatten(zeroOrMore(match(any()))),
                        toContentString()
                )
        );

        // when
        ExampleResult result = new Parser(grammar)
                .parse(input)
                .output(transformation());

        // then
        assertEquals("test ", result.content);
        assertEquals(TestCode.OUTPUT, result.code);
        assertEquals(" 123", result.remaining);
    }

    private Transformation<ExampleResult> transformation() {
        return Transformations.fromContentList(new Function<ListTransformationRequest, ExampleResult>() {
            @Override
            public ExampleResult apply(ListTransformationRequest input) {
                return new ExampleResult(input.get(0, String.class), input.get(1, TestCode.class), input.get(2, String.class));
            }
        });
    }


    public enum TestCode {
        CODE,
        OUTPUT,
        COMMENT
    }

    public static class ExampleResult {
        private final String content;
        private final TestCode code;
        private final String remaining;

        public ExampleResult(String content, TestCode code, String remaining) {
            this.content = content;
            this.code = code;
            this.remaining = remaining;
        }
    }
}
