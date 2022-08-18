package com.moyu.framework.message;

import com.moyu.framework.message.support.I18nMessageSupport;

/**
 * @ClassName: MessageHelper.java<br>
 * @Description: 获取国际化消息帮助类 之后将适配更多的获取信息的途径
 * @author:
 */
public class MessageHelper {

  public static String getI18nMessage(String name) {
    return I18nMessageSupport.getI18nMessage(name);
  }

  public static String getI18nMessage(String name, String defaultValue) {
    return I18nMessageSupport.getI18nMessage(name, defaultValue);
  }
}
