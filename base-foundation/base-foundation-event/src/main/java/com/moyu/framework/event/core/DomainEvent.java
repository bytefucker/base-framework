package com.moyu.framework.event.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author
 * @date 2020-05-08 15:22
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainEvent {

  /**
   * 事件的topic
   *
   * @return topic
   */
  String topic();

  /**
   * 来源系统编号
   *
   * @return system
   */
  String fromSystem();

}
