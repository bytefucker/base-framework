package com.moyu.framework.format.builder;

import com.moyu.framework.exception.BaseException;
import com.moyu.framework.format.ErrorDefine;

/**
 * @ClassName: PreDefinedExceptionBuilder.java<br>
 * @Description: 异常构造器
 * @author:
 */
public class PreDefinedExceptionBuilder {

  private ErrorDefine errorDefine;
  private String msg;
  private String debug;
  private Throwable cause;
  private Object[] args;

  public PreDefinedExceptionBuilder(ErrorDefine errorDefine) {
    this.errorDefine = errorDefine;
  }

  public static PreDefinedExceptionBuilder builder(ErrorDefine errorDefine) {
    return new PreDefinedExceptionBuilder(errorDefine);
  }

  public PreDefinedExceptionBuilder msg(String msg) {
    this.msg = msg;
    return this;
  }

  public PreDefinedExceptionBuilder debug(String debug) {
    this.debug = debug;
    return this;
  }

  public PreDefinedExceptionBuilder cause(Throwable cause) {
    this.cause = cause;
    return this;
  }

  public PreDefinedExceptionBuilder args(Object... args) {
    this.args = args;
    return this;
  }

  @Deprecated
  public PreDefinedExceptionBuilder extend(Object... args) {
    this.args = args;
    return this;
  }

  @Deprecated
  public PreDefinedExceptionBuilder setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  @Deprecated
  public PreDefinedExceptionBuilder setDebug(String debug) {
    this.debug = debug;
    return this;
  }

  @Deprecated
  public PreDefinedExceptionBuilder setEx(Throwable cause) {
    this.cause = cause;
    return this;
  }

  @Deprecated
  public PreDefinedExceptionBuilder setArgs(Object... args) {
    this.args = args;
    return this;
  }

  public BaseException build() {
    return errorDefine.exception(msg, debug, cause, args);
  }
}
