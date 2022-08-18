package com.moyu.framework.event.exception;

/**
 * 事件类型定义必须显示声明注解
 *
 * @author
 * @date 2020-05-08 16:20
 **/
public class MissingAnnotationException extends RuntimeException {

  public MissingAnnotationException(String msg) {
    super(msg);
  }

}
