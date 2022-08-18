package com.moyu.framework.exception;

/**
 *
 * @ClassName: InstanceException.java<br>
 * @Description: 实例生成异常
 * @author:
 */
public class InstanceException extends BaseException {

  public InstanceException() {
  }

  public InstanceException(Integer code, String msg) {
    super(code, msg);
  }

  public InstanceException(Integer code, String msg, String debugMsg) {
    super(code, msg, debugMsg);
  }

  public InstanceException(Integer code, String msg, String debugMsg, Throwable cause) {
    super(code, msg, debugMsg, cause);
  }
}
