package com.moyu.framework.code.annotation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 1、key的顺序：
 * 优先获取i18nKey，没有，获取msg，没有，获取枚举name
 * 2、msg信息（拿着上一步的key去获取国际化）：
 * 优先获取i18nKey对应的
 */
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
