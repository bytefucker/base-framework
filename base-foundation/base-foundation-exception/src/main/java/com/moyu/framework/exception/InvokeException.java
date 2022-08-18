package com.moyu.framework.exception;

public class InvokeException extends BaseException {

  public InvokeException() {
  }

  public InvokeException(Integer code, String msg) {
    super(code, msg);
  }

  public InvokeException(Integer code, String msg, String debugMsg) {
    super(code, msg, debugMsg);
  }

  public InvokeException(Integer code, String msg, String debugMsg, Throwable cause) {
    super(code, msg, debugMsg, cause);
  }
}
