/**
 * 
 */

package com.xyz.mybatis.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author lee.
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Inherited
@Documented
public @interface Transactional {

  String value() default "";

  boolean isReadOnly() default false;

}
