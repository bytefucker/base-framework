package com.moyu.framework.format;

import com.moyu.framework.code.annotation.CodeDeclare;
import com.moyu.framework.format.annotation.NormalDeclare;
import java.lang.reflect.Field;

/**
 * 正常返回定义 一、进行正常码定义时，按如下方式即可： public enum XxxxCode implements NormalDefine{
 *
 * @NormalDeclare(code = @CodeDeclare(value = 100001,msg = "test_error")) NORMAL_CODE, ... }
 * 二、编写时有如下限定(注意，与异常码定义不同)： 1、类型为枚举：enum 2、继承NormalDefine 3、枚举值上注解配置@NormalDeclare，其中code为码和说明信息
 * 以上三点不满足时，运行时会抛出异常
 * <p>
 * 三、使用时按如下方式： 1、获取标准格式输出值：return XxxxCode.NORMAL_CODE.buildResponse(T data);
 */
public interface NormalDefine extends Format {

  @Override
  default CodeDeclare code(Field field) {
    NormalDeclare normalDefine = field.getAnnotation(NormalDeclare.class);
    return normalDefine != null ? normalDefine.code() : Format.super.code(field);
  }
}
