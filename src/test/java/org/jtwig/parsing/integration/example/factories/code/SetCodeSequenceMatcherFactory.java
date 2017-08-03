package org.jtwig.parsing.integration.example.factories.code;

import com.google.common.base.Function;
import org.jtwig.parsing.integration.example.factories.SequenceMatcherFactory;
import org.jtwig.parsing.integration.example.factories.expression.IdentifierSequenceMatcherFactory;
import org.jtwig.parsing.integration.example.factories.expression.IntegerLiteralSequenceMatcherFactory;
import org.jtwig.parsing.integration.example.model.SetCode;
import org.jtwig.parsing.sequence.SequenceMatcher;
import org.jtwig.parsing.transform.ListTransformationRequest;
import org.jtwig.parsing.transform.Transformations;

import static org.jtwig.parsing.sequence.SequenceMatchers.sequence;
import static org.jtwig.parsing.sequence.SequenceMatchers.string;
import static org.jtwig.parsing.sequence.SequenceMatchers.transform;
import static org.jtwig.parsing.sequence.SequenceMatchers.whitespaces;

public class SetCodeSequenceMatcherFactory implements SequenceMatcherFactory {
    private final IdentifierSequenceMatcherFactory identifierSequenceMatcherFactory;
    private final IntegerLiteralSequenceMatcherFactory integerLiteralSequenceMatcherFactory;

    public SetCodeSequenceMatcherFactory(IdentifierSequenceMatcherFactory identifierSequenceMatcherFactory, IntegerLiteralSequenceMatcherFactory integerLiteralSequenceMatcherFactory) {
        this.identifierSequenceMatcherFactory = identifierSequenceMatcherFactory;
        this.integerLiteralSequenceMatcherFactory = integerLiteralSequenceMatcherFactory;
    }

    @Override
    public SequenceMatcher create() {
        return transform(
                sequence(
                        sequence(
                                string("set"),
                                whitespaces()
                        ),

                        identifierSequenceMatcherFactory.create(),

                        sequence(
                                whitespaces(),
                                string("="),
                                whitespaces()
                        ),

                        integerLiteralSequenceMatcherFactory.create(),

                        whitespaces()
                ),
                Transformations.fromContentList(new Function<ListTransformationRequest, SetCode>() {
                    @Override
                    public SetCode apply(ListTransformationRequest input) {
                        return new SetCode(input.get(0, String.class), input.get(1, Integer.class));
                    }
                })
        );
    }
}
