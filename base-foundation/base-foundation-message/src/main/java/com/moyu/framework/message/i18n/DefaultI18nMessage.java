package com.moyu.framework.message.i18n;

/**
 * @ClassName: DefaultI18nMessage.java<br>
 * @Description: 默认国际化实现
 * @author:
 */
public class DefaultI18nMessage implements I18nMessage {

  @Override
  public String getMessage(String name) {
    return this.getMessage(name, name);
  }

  @Override
  public String getMessage(String name, String defaultValue) {
    return defaultValue;
  }
}
