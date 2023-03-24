package com.moyu.framework.code.annotation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeDeclare {

  /**
   * 码
   */
  int value();

  /**
   * 组
   */
  int group() default 0;

  /**
   * 码说明
   */
  String msg() default "";

  /**
   * i18n key
   */
  String i18nKey() default "";
}
