package com.xyz.mybatis.core.scanner.filter;

public abstract class AbstractSupperClassFilter extends AbstractClassFilter {

  protected final Class<?> superClass;

  protected AbstractSupperClassFilter(String packageName, Class<?> superClass) {
    super(packageName);
    this.superClass = superClass;
  }

  public AbstractSupperClassFilter(String packageName, Class<?> superClass, ClassLoader loader) {
    super(packageName, loader);
    this.superClass = superClass;
  }
}
