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
      try {
        method = object.getClass().getMethod(method.getName(), method.getParameterTypes());
      } catch (Exception err) {
        err.printStackTrace();
      }
      annotation = method.getAnnotation(Transactional.class);
    }
    return annotation;
  }

}
