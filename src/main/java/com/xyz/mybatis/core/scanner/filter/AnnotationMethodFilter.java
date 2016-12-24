package com.xyz.mybatis.core.scanner.filter;

import java.lang.annotation.Annotation;

public abstract class AnnotationMethodFilter extends AbstractMethodFilter {

  protected final Class<? extends Annotation> annotationType;

  protected AnnotationMethodFilter(Class<?> clazz, Class<? extends Annotation> annotationType) {
    super(clazz);
    this.annotationType = annotationType;
  }

}
