package com.moyu.framework.message.support;

import com.moyu.framework.message.i18n.DefaultI18nMessage;
import com.moyu.framework.message.i18n.I18nMessage;
import java.util.Iterator;
import java.util.ServiceLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @ClassName: I18nMessageSupport.java<br>
 * @Description: 获取国际化消息
 * @author:
 */
public class I18nMessageSupport {

  private static final Logger logger = LoggerFactory.getLogger(I18nMessageSupport.class);
  private static final ServiceLoader<I18nMessage> loader;
  private static final DefaultI18nMessage defaultI18nMessage = new DefaultI18nMessage();

  /**
   * 默认开启国际化
   */
  private static boolean isNeedI18n = true;
  /**
   * 是否打印错误日志
   */
  private static boolean printMessageErrorLog = false;

  static {
    // 静态变量和静态块 初始化没有区别，放在哪儿都行
    loader = ServiceLoader.load(I18nMessage.class);
    Iterator<I18nMessage> msgIterator = loader.iterator();

    while (msgIterator.hasNext()) {
      msgIterator.next();
    }
  }

  /**
   * 获取国际化消息
   *
   * @param name
   * @return 若无实现，默认返回name
   */
  public static String getI18nMessage(String name) {
    return getI18nMessage(name, name);
  }

  /**
   * 获取国际化消息
   *
   * @param name
   * @param defaultValue
   * @return 若无实现，默认返回 defaultValue
   */
  public static String getI18nMessage(String name, String defaultValue) {
    String message = null;
    Iterator<I18nMessage> msgIterator = loader.iterator();
    // 不需要国际化 或 没有实现
    if (!isNeedI18n || !msgIterator.hasNext()) {
      // 默认实现
      return defaultI18nMessage.getMessage(name, defaultValue);
    }
    while (msgIterator.hasNext()) {
      I18nMessage i18nMessage = msgIterator.next();
      message = i18nMessage.getMessage(name, defaultValue);
      if (StringUtils.isNotEmpty(message)) {
        return message;
      }
    }
    if (I18nMessageSupport.isPrintMessageErrorLog()) {
      logger.warn("未找到{}的国际化文案配置，请添加", name);
    }
    return defaultValue;
  }

  public static boolean isIsNeedI18n() {
    return isNeedI18n;
  }

  public static void setIsNeedI18n(boolean isNeedI18n) {
    I18nMessageSupport.isNeedI18n = isNeedI18n;
  }

  public static boolean isPrintMessageErrorLog() {
    return printMessageErrorLog;
  }

  public static void enablePrintMessageErrorLog() {
    printMessageErrorLog = true;
  }

  public static void disablePrintMessageErrorLog() {
    printMessageErrorLog = false;
  }
}
