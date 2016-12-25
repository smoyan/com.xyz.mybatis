/**
 * 
 */

package com.xyz.mybatis.core.annotation;

import java.lang.reflect.Method;

/**
 * @author lee.
 */
public class AnnotationUtils {

  public static Transactional getTransactionalAnnotation(Object object, Method method) throws NoSuchMethodException, SecurityException {
    Transactional annotation = object.getClass().getAnnotation(Transactional.class);
    if (annotation == null) {
      annotation = method.getAnnotation(Transactional.class);
      if (annotation == null) {
        method = object.getClass().getMethod(method.getName(), method.getParameterTypes());
        annotation = method.getAnnotation(Transactional.class);
      }
    }
    return annotation;
  }

}
