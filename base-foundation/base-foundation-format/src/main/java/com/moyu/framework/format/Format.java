package com.moyu.framework.format;

import com.moyu.framework.code.annotation.CodeDeclare;
import com.moyu.framework.message.i18n.Section;
import com.moyu.framework.message.support.I18nMessageSupport;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * yhz
 *
 * @author
 */
public interface Format {

  Logger logger = LoggerFactory.getLogger(Format.class);

  /**
   * 获取code定义
   *
   * @param field instance
   * @return CodeDeclare
   */
  default CodeDeclare code(Field field) {
    return field.getAnnotation(CodeDeclare.class);
  }

  /**
   * 获取code定义
   *
   * @return CodeDeclare
   */
  default CodeDeclare codeDeclare() {
    // 判断是否按规范使用了枚举
    if (!(this instanceof Enum)) {
      return null;
    }
    CodeDeclare code = null;
    try {
      Enum<?> em = (Enum<?>) this;
      // 获取注解信息
      code = code(this.getClass().getField(em.name()));
    } catch (NoSuchFieldException e) {
      logger.warn("code parser filed:", e);
    }
    return code;
  }

  /**
   * 获取配置的code值
   *
   * @return code
   */
  default int code() {
    CodeDeclare code = codeDeclare();
    if (code != null) {
      return code.value();
    }
    logger.error("获取code码失败");
    return -1;
  }

  /**
   * 获取配置的msg值，若没有配置，返回枚举name，若是需要国际化，则返回对应国际化文案
   *
   * @return String
   */
  default String msg() {
    CodeDeclare code = codeDeclare();
    if (code != null) {
      return I18nMessageSupport.getI18nMessage(code.i18nKey(),
          StringUtils.isEmpty(code.msg()) ? code.i18nKey() : code.msg());
    }
    logger.error("获取msg失败");
    return null;
  }

  /**
   * 获取配置的i18nKey值，若没有配置，返回枚举name，若是需要国际化，则返回对应国际化文案
   *
   * @return String
   */
  default String i18nKey() {
    CodeDeclare code = codeDeclare();
    if (code != null) {
      return code.i18nKey();
    }
    logger.error("获取i18nKey失败");
    return null;
  }

  /**
   * 获取code所在分组，目前用于国际化分组字段
   *
   * @return List<String>
   */
  default List<String> belongSections() {
    Section[] sections = this.getClass().getAnnotationsByType(Section.class);
    if (sections.length != 0) {
      Set<String> set = new HashSet<>();
      for (Section group : sections) {
        set.add(group.value());
      }
      return new ArrayList<>(set);
    }
    return Collections.emptyList();
  }
}

