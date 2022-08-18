package com.moyu.framework.exception;


/**
 * 配置错误异常类
 */
public class ConfigErrorException extends BaseException {

  public ConfigErrorException() {
    super();
  }

  public ConfigErrorException(Integer code, String msg) {
    super(code, msg);
  }

  public ConfigErrorException(Integer code, String msg, String debugMsg) {
    super(code, msg, debugMsg);
  }

  public ConfigErrorException(Integer code, String msg, String debugMsg, Throwable ex) {
    super(code, msg, debugMsg, ex);
  }
}
