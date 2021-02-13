package com.springfilter.node.predicate;

import java.util.LinkedList;

import com.springfilter.FilterParser;
import com.springfilter.compiler.Extensions;
import com.springfilter.compiler.exception.InputExpected;
import com.springfilter.compiler.exception.OutOfTokenException;
import com.springfilter.compiler.node.INode;
import com.springfilter.compiler.node.Matcher;
import com.springfilter.compiler.token.IToken;
import com.springfilter.node.IExpression;
import com.springfilter.token.Comparator;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod(Extensions.class)
public class ConditionMatcher extends Matcher<Condition> {

  public static final ConditionMatcher INSTANCE = new ConditionMatcher();

  @Override
  public Condition match(LinkedList<Matcher<?>> matchers, LinkedList<IToken> tokens, LinkedList<INode> nodes)
      throws InputExpected {

    IExpression left = FilterParser.walk(matchers, tokens, nodes, false);

    if (left != null) {

      if (tokens.indexIs(Comparator.class)) {

        Comparator comparator = (Comparator) tokens.take();

        if (comparator.needsInput()) {

          IExpression right = FilterParser.walk(matchers, tokens, nodes, false);

          if (right != null) {
            return ConditionInfix.builder().left(left).comparator(comparator).right(right).build();
          }

          throw new OutOfTokenException("The comparator " + comparator.getLiteral() + " expects an expression");

        }

        return ConditionPostfix.builder().left(left).comparator(comparator).build();

      }

    }

    return null;

  }

}
