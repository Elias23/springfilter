package com.turkraft.springfilter.language;

import com.turkraft.springfilter.definition.FilterFunction;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeFunction extends FilterFunction {

  public LocalDateTimeFunction() {
    super("localDateTime");
  }

}
