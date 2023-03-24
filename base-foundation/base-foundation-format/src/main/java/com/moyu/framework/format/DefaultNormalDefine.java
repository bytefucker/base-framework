package com.moyu.framework.format;


import static com.moyu.framework.format.constants.SectionConstants.BASE_FRAMEWORK_GROUP;

import com.moyu.framework.code.annotation.CodeDeclare;
import com.moyu.framework.format.annotation.NormalDeclare;
import com.moyu.framework.message.i18n.Section;

@Section(BASE_FRAMEWORK_GROUP)
public enum DefaultNormalDefine implements NormalDefine {

  /**
   * 成功
   */
  @NormalDeclare(code = @CodeDeclare(value = 0))
  SUCCESS
}
