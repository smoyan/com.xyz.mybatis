package com.xyz.mybatis.core.scanner.filter;

import java.lang.annotation.Annotation;

public abstract class AbstractAnnotationClassFilter extends AbstractClassFilter {

  protected final Class<? extends Annotation> annotationClass;

  protected AbstractAnnotationClassFilter(String packageName, Class<? extends Annotation> annotationClass) {
    super(packageName);
    this.annotationClass = annotationClass;
  }

  public AbstractAnnotationClassFilter(String packageName, Class<? extends Annotation> annotationClass, ClassLoader loader) {
    super(packageName, loader);
    this.annotationClass = annotationClass;
  }
}
