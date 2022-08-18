package com.moyu.framework.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.helpers.MessageFormatter;

/**
 * 异常类基类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseException extends RuntimeException {

  private Integer code;
  private String msg;
  private String debug;
  private Object[] args;

  public BaseException() {
    super();
  }

  public BaseException(Integer code, String msg, String debugMsg, Throwable cause, Object[] args) {
    super(msg, cause);
    this.code = code;
    this.msg = msg;
    this.debug = debugMsg;
    this.args = args;
  }

  public BaseException(Integer code, String msg) {
    this(code, msg, null, null, null);
  }

  public BaseException(Integer code, String msg, Object[] args) {
    this(code, msg, null, null, args);
  }

  public BaseException(Integer code, String msg, String debugMsg) {
    this(code, msg, debugMsg, null, null);
  }

  public BaseException(Integer code, String msg, String debugMsg, Object[] args) {
    this(code, msg, debugMsg, null, args);
  }

  public BaseException(Integer code, String msg, String debugMsg, Throwable cause) {
    this(code, msg, debugMsg, cause, null);
  }

  /* 异常 默认的构造器：适应可能不会有code的情况 */

  public BaseException(String message) {
    super(message);
    this.msg = message;
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
    this.msg = message;
  }

  public BaseException(Throwable cause) {
    super(cause);
    if (cause instanceof BaseException) {
      BaseException origin = (BaseException) cause;
      this.code = origin.getCode();
      this.debug = origin.getDebug();
      this.msg = origin.getMsg();
    }
  }

  public BaseException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.msg = message;
  }

  public String getMessage() {
    return getMsg();
  }

  public String getMsg() {
    if (args == null || args.length == 0) {
      return msg;
    } else {
      // 支持直接{}占位符方式
      if (msg.contains("{}")) {
        return MessageFormatter.arrayFormat(msg, args).getMessage();
      }
      return PlaceholderHelper.INSTANCE.replacePlaceholders(msg, args);
    }
  }
}
