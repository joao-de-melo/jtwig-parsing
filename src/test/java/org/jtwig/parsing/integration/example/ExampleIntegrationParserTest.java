package org.jtwig.parsing.integration.example;

import org.jtwig.parsing.Parser;
import org.jtwig.parsing.explain.ExplainationPrinter;
import org.jtwig.parsing.integration.example.config.ParserConfiguration;
import org.jtwig.parsing.integration.example.factories.JtwigCodeSequenceMatcherFactory;
import org.jtwig.parsing.integration.example.factories.JtwigContentSequenceMatcherFactory;
import org.jtwig.parsing.integration.example.factories.JtwigParserFactory;
import org.jtwig.parsing.integration.example.factories.code.SetCodeSequenceMatcherFactory;
import org.jtwig.parsing.integration.example.factories.expression.IdentifierSequenceMatcherFactory;
import org.jtwig.parsing.integration.example.factories.expression.IntegerLiteralSequenceMatcherFactory;
import org.jtwig.parsing.transform.Transformations;
import org.jtwig.parsing.tree.Node;
import org.junit.Test;

public class ExampleIntegrationParserTest {
    @Test
    public void exampleTest() throws Exception {
        ParserConfiguration configuration = new ParserConfiguration("{%", "%}", "{{", "}}", "{#", "#}");

        JtwigParserFactory factory = new JtwigParserFactory(
                new JtwigCodeSequenceMatcherFactory(configuration, new SetCodeSequenceMatcherFactory(new IdentifierSequenceMatcherFactory(), new IntegerLiteralSequenceMatcherFactory())),
                new JtwigContentSequenceMatcherFactory(configuration)
        );

        new ExplainationPrinter().print(factory.create().explain(), System.out);

        Node output = new Parser(factory.create())
                .parse("Test {% set one = 1 -%} hi")
                .output(Transformations.identity());

        System.out.println(output);
    }
}
