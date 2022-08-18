package com.moyu.framework.format;


import com.moyu.framework.code.annotation.CodeDeclare;
import com.moyu.framework.exception.BaseException;
import com.moyu.framework.exception.DefaultBaseException;
import com.moyu.framework.exception.annotation.ExceptionDeclare;
import com.moyu.framework.exception.builder.ExceptionBuilder;
import com.moyu.framework.format.annotation.ErrorDeclare;
import com.moyu.framework.format.builder.PreDefinedExceptionBuilder;
import com.moyu.framework.message.MessageHelper;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface ErrorDefine extends Format {

  Logger log = LoggerFactory.getLogger(ErrorDefine.class);

  /**
   * 构造抛出异常数据并返回异常供抛出
   *
   * @return
   */
  default BaseException exception() {
    return exception(null, null, null, null);
  }

  /**
   * 传入msg占位符替换参数，如msg="异常信息:{0},详情为：{1}",此时传入["系统异常","参数传递错误，请联系管理员"]
   * 则最终msg输出为："异常信息:系统异常,详情为：参数传递错误，请联系管理员"
   *
   * @param args 值空间
   * @return
   */
  default BaseException exceptionWithArgs(Object... args) {
    return exception(null, null, null, args);
  }

  /**
   * 构造抛出异常数据并返回异常供抛出
   *
   * @param msg  运行时自定义异常信息
   * @param args
   * @return
   */
  default BaseException exception(String msg, Object... args) {
    return exception(msg, null, null, args);
  }

  /**
   * 构造抛出异常数据并返回异常供抛出
   *
   * @param msg
   * @param debugMsg
   * @param args
   * @return
   */
  default BaseException exception(String msg, String debugMsg, Object... args) {
    return exception(msg, debugMsg, null, args);
  }

  /**
   * 构造抛出异常数据并返回异常供抛出
   *
   * @param cause 抛出时异常
   * @param args
   * @return
   */
  default BaseException exception(Throwable cause, Object... args) {
    return exception(null, null, cause, args);
  }

  /**
   * @param msg
   * @param cause
   * @param args
   * @return
   */
  default BaseException exception(String msg, Throwable cause, Object... args) {
    return exception(msg, null, cause, args);
  }

  /**
   * 构造抛出异常数据并返回异常供抛出
   *
   * @param msg      运行时自定义异常信息
   * @param debugMsg 输出到接口中debug信息
   * @param cause    抛出时异常
   * @param args     占位符值空间
   * @return 基础异常
   */
  default BaseException exception(String msg, String debugMsg, Throwable cause, Object... args) {
    BaseException baseException = null;
    // 判断是否按规范使用了枚举
    if (this instanceof Enum) {
      try {
        Enum<?> em = (Enum<?>) this;
        Field emInstance = this.getClass().getField(em.name());
        CodeDeclare codeDeclare = codeDeclare();
        if (codeDeclare == null) {
          throw DefaultErrorDefine.UNSTANDARD_CONFIG_ERROR.exception();
        }
        ExceptionDeclare exception = exceptionDeclare(emInstance);
        Class<? extends BaseException> exceptionClass =
            exception == null ? DefaultBaseException.class : exception.value();
        // 开始构造返回msg
        String message;

        // 判断传入值msg
        if (StringUtils.isNotEmpty(msg)) {
          message = msg;
        } else {
          message = MessageHelper.getI18nMessage(codeDeclare.i18nKey(),
              StringUtils.isEmpty(codeDeclare.msg()) ? codeDeclare.i18nKey() : codeDeclare.msg());
        }
        // 构造异常实例
        baseException = ExceptionBuilder.builder(exceptionClass)
            .code(codeDeclare.value() + codeDeclare.group())
            .msg(message)
            .cause(cause)
            .debug(debugMsg)
            .args(args)
            .build();
      } catch (NoSuchFieldException e) {
        log.error("异常码定义配置不规范:", e);
        throw DefaultErrorDefine.UNSTANDARD_CONFIG_ERROR.exception(e);
      }
    }
    // 以下情况：不是枚举、没拿到注解，会进入此判断
    if (baseException == null) {
      // 返回未知错误
      throw DefaultErrorDefine.UNSTANDARD_CONFIG_ERROR.exception();
    }
    return baseException;
  }


  @Override
  default CodeDeclare code(Field field) {
    // 获取注解信息
    ErrorDeclare errorDeclare = field.getAnnotation(ErrorDeclare.class);
    return errorDeclare != null ? errorDeclare.code() : Format.super.code(field);
  }

  /**
   * 获取异常定义
   *
   * @param emInstance
   * @return
   */
  default ExceptionDeclare exceptionDeclare(Field emInstance) {
    ExceptionDeclare exception;
    // 获取注解信息
    ErrorDeclare errorDeclare = emInstance.getAnnotation(ErrorDeclare.class);
    // 是否拿到了注解
    if (errorDeclare != null) {
      exception = errorDeclare.exception();
    } else {
      // 尝试直接拿各个注解
      exception = emInstance.getAnnotation(ExceptionDeclare.class);
    }
    return exception;
  }

  /**
   * 构造器
   *
   * @return
   */
  default PreDefinedExceptionBuilder builder() {
    return new PreDefinedExceptionBuilder(this);
  }
}

