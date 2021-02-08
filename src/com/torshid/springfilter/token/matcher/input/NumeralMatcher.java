package com.torshid.springfilter.token.matcher.input;

import java.text.NumberFormat;
import java.text.ParsePosition;

import com.torshid.compiler.Extensions;
import com.torshid.compiler.token.matcher.Matcher;
import com.torshid.springfilter.token.input.Numeral;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod(Extensions.class)
public class NumeralMatcher extends Matcher<Numeral> {

  @Override
  public Numeral match(StringBuilder input) {

    ParsePosition position = new ParsePosition(0);

    Number number = NumberFormat.getInstance().parse(input.toString(), position);

    if (number != null) {
      input.take(position.getIndex());
      return Numeral.builder().value(number).build();
    }

    return null;

  }

}
