package com.xyz.mybatis.core.scanner.filter;

public abstract class AbstractPatternNameMethodFilter extends AbstractMethodFilter {

  protected final String methodPattern;

  protected AbstractPatternNameMethodFilter(Class<?> clazz, String methodPattern) {
    super(clazz);
    this.methodPattern = methodPattern;
  }

}
