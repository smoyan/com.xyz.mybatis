/**
 * 
 */

package com.xyz.mybatis.core.annotation;

import java.lang.reflect.Method;

/**
 * @author lee.
 */
public class AnnotationUtils {

  public static Transactional getTransactionalAnnotation(Object object, Method method) {
    Transactional annotation = object.getClass().getAnnotation(Transactional.class);
    if (annotation == null) {
      annotation = method.getAnnotation(Transactional.class);
    }
    return annotation;
  }

}
