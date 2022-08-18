package com.moyu.framework.exception;

/**
 * 默认实现异常类
 */
public class DefaultBaseException extends BaseException {

  public DefaultBaseException() {
    super();
  }

  public DefaultBaseException(Integer code, String msg) {
    super(code, msg);
  }

  public DefaultBaseException(Integer code, String msg, String debugMsg) {
    super(code, msg, debugMsg);
  }

  public DefaultBaseException(Integer code, String msg, String debugMsg, Throwable ex) {
    super(code, msg, debugMsg, ex);
  }

  public DefaultBaseException(Integer code, String msg, String debugMsg, Throwable ex,
      Object[] args) {
    super(code, msg, debugMsg, ex, args);
  }

  public DefaultBaseException(Throwable throwable) {
    super(throwable);
  }
}
