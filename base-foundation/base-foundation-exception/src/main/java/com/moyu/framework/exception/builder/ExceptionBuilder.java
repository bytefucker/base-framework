package com.moyu.framework.exception.builder;

import com.moyu.framework.exception.BaseException;
import com.moyu.framework.exception.DefaultBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ExceptionBuilder.java<br>
 * @Description: 异常实例构造器
 * @author:
 */
public class ExceptionBuilder<T extends BaseException> {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionBuilder.class);

  private Class<T> exceptionClass;
  private Integer code;
  private String msg;
  private String debug;
  private Throwable cause;
  private Object[] args;
  private ExceptionEnhance<T> exceptionEnhance;

  public ExceptionBuilder(Class<T> exceptionClass) {
    this.exceptionClass = exceptionClass;
  }

  public static ExceptionBuilder<DefaultBaseException> builder() {
    return new ExceptionBuilder<>(DefaultBaseException.class);
  }

  public static <T extends BaseException> ExceptionBuilder<T> builder(Class<T> exceptionClass) {
    return new ExceptionBuilder<>(exceptionClass);
  }

  public ExceptionBuilder<T> code(Integer code) {
    this.code = code;
    return this;
  }

  public ExceptionBuilder<T> msg(String msg) {
    this.msg = msg;
    return this;
  }

  public ExceptionBuilder<T> debug(String debug) {
    this.debug = debug;
    return this;
  }

  public ExceptionBuilder<T> cause(Throwable cause) {
    this.cause = cause;
    return this;
  }

  public ExceptionBuilder<T> args(Object... args) {
    this.args = args;
    return this;
  }

  public ExceptionBuilder<T> enhance(ExceptionEnhance<T> exceptionEnhance) {
    this.exceptionEnhance = exceptionEnhance;
    return this;
  }

  @Deprecated
  public ExceptionBuilder<T> extend(Object... args) {
    this.args = args;
    return this;
  }

  /**
   * 构造异常
   *
   * @return
   */
  public T build() {
    T exception;
    // 此判断用于避免大多数情况使用反射
    if (exceptionClass == DefaultBaseException.class) {
      //noinspection unchecked
      exception = (T) new DefaultBaseException(code, msg, debug, cause, args);
    } else {
      // 通过反射获取实例
      // 首先，尝试获取带参数的构造函数，此时，若继承类未显示构造该构造器，则会直接报错，所以自己捕获，并继续接下来的无参构造
      exception = Reflect.getConstructor(
          exceptionClass,
          new Class[]{Integer.class, String.class, String.class, Throwable.class, Object[].class},
          new Object[]{code, msg, debug, cause, args});
      if (exception == null) {
        // 然后，获取无参的构造函数（仅当带参构造失败时进行）
        exception = Reflect.getDefaultConstructor(exceptionClass);
        if (exception == null) {
          // 实例化错误（未找到默认构造器）
          logger.error("exception instance error:can't find default public construct!");
          // throw DefaultErrorDefine.INSTANCE_ERROR.exception("exception instance error:can't find default public construct!");
          throw new RuntimeException(
              "exception instance error:can't find default public construct!");
        } else {
          // 为使用默认构造器生成的异常进行默认赋值
          exception.setCode(code);
          exception.setDebug(debug);
          exception.setMsg(msg);
          exception.initCause(cause);
          exception.setArgs(args);
        }
      }
    }
    if (exceptionEnhance != null) {
      exceptionEnhance.call(exception);
    }
    return exception;
  }

  @FunctionalInterface
  public interface ExceptionEnhance<T> {

    void call(T t);
  }
}
