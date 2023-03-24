package com.moyu.framework.format;

import static com.moyu.framework.format.constants.SectionConstants.BASE_FRAMEWORK_GROUP;

import com.moyu.framework.code.annotation.CodeDeclare;
import com.moyu.framework.format.annotation.ErrorDeclare;
import com.moyu.framework.message.i18n.Section;

@Section(BASE_FRAMEWORK_GROUP)
public enum DefaultErrorDefine implements ErrorDefine {

  /**
   * 服务器错误
   */
  @ErrorDeclare(code = @CodeDeclare(value = 10000))
  SERVER_ERROR()
}
