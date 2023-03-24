package com.moyu.framework.code;


import static com.moyu.framework.code.constants.FieldConstants.FIELD_I18N_KEY;
import static com.moyu.framework.code.constants.FieldConstants.FIELD_MSG;
import static com.moyu.framework.code.constants.FieldConstants.MEMBER_VALUES;

import com.moyu.framework.code.annotation.CodeDeclare;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CodeParser {

  /**
   * 处理注解（按相应默认规则进行填充）
   *
   * @param code    注解
   * @param objName 默认名称
   */
  public static CodeDeclare parse(CodeDeclare code, String objName) {
    try {
      // 获取到了注解
      if (code == null) {
        return null;
      }
      // 如果没有配置i18nKey或msg（仅会进入一次）
      if (StringUtils.isEmpty(code.i18nKey()) || StringUtils.isEmpty(code.msg())) {
        // 此时需要将msg赋值为枚举name
        // 获取codeDecalre注解实例的invocationHandler
        InvocationHandler handler = Proxy.getInvocationHandler(code);
        // 获取handler中的memberValues
        Field codeField = handler.getClass().getDeclaredField(MEMBER_VALUES);
        // 设置为可访问
        codeField.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) codeField.get(handler);
        // 修改msg属性值
        if (StringUtils.isEmpty(code.msg())) {
          memberValues.put(FIELD_MSG, objName);
        }
        // 修改i8nKey属性值
        if (StringUtils.isEmpty(code.i18nKey())) {
          memberValues.put(FIELD_I18N_KEY, code.msg());
        }
      }
    } catch (NoSuchFieldException e) {
      log.error("未找到该字段", e);
    } catch (IllegalAccessException e) {
      log.error("没有字段访问权限", e);
    }
    return code;
  }
}
