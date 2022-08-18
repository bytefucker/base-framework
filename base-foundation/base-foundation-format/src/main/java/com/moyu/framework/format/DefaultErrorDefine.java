package com.moyu.framework.format;

import static com.moyu.framework.format.constants.SectionConstants.BASE_FRAMEWORK_GROUP;

import com.moyu.framework.code.annotation.CodeDeclare;
import com.moyu.framework.exception.ConfigErrorException;
import com.moyu.framework.exception.InstanceException;
import com.moyu.framework.exception.InvokeException;
import com.moyu.framework.exception.annotation.ExceptionDeclare;
import com.moyu.framework.format.annotation.ErrorDeclare;
import com.moyu.framework.message.i18n.Section;

@Section(BASE_FRAMEWORK_GROUP)
public enum DefaultErrorDefine implements ErrorDefine {
  /**
   * 通用码定义
   */
  // 异常构造实例化出错
  @ErrorDeclare(code = @CodeDeclare(value = 10000001, group = 1), exception = @ExceptionDeclare(InstanceException.class))
  INSTANCE_ERROR,
  // 非标准配置方式
  @ErrorDeclare(code = @CodeDeclare(value = 10000002), exception = @ExceptionDeclare(ConfigErrorException.class))
  UNSTANDARD_CONFIG_ERROR,
  // 传入参数空指针异常
  @ErrorDeclare(code = @CodeDeclare(value = 10000003))
  PARAM_NULL_POINT_ERROR,
  // 方法调用异常
  @ErrorDeclare(code = @CodeDeclare(value = 10000004), exception = @ExceptionDeclare(InvokeException.class))
  INVOKE_METHOD_ERROR,
  // 未知异常
  @ErrorDeclare(code = @CodeDeclare(value = 10000099))
  UNKNOWN_ERROR,

  // 服务错误
  @ErrorDeclare(code = @CodeDeclare(value = 10001000))
  SERVER_ERROR,
  // 参数校验失败
  @ErrorDeclare(code = @CodeDeclare(value = 10001001))
  PARAM_VALIDATE_ERROR,
  // 操作失败
  @ErrorDeclare(code = @CodeDeclare(value = 10001002))
  OPERATE_FAILED,
  // 无权操作
  @ErrorDeclare(code = @CodeDeclare(value = 10001003))
  NON_OPERATE_ACCESS,
  // 操作被禁止
  @ErrorDeclare(code = @CodeDeclare(value = 10001004))
  OPERATE_FORBIDDEN
}
