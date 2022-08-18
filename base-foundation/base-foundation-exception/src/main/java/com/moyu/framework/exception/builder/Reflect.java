package com.moyu.framework.exception.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射构造类
 */
class Reflect {

  private static Logger log = LoggerFactory.getLogger(Reflect.class);

  /**
   * 获取带参构造函数
   *
   * @param cls
   * @param clazzTypes
   * @param parameters
   * @param <T>
   * @return
   */
  public static <T> T getConstructor(Class<T> cls, Class[] clazzTypes, Object[] parameters) {
    T obj = null;
    // 首先尝试获取带参数的构造函数，此时，若继承类未显示构造该构造器，则会直接报错，所以自己捕获，并继续接下来的无参构造
    try {
      Constructor<T> constructor = cls.getDeclaredConstructor(clazzTypes);
      if (constructor != null) {
        constructor.setAccessible(true);
        obj = constructor.newInstance(parameters);
      }
    } catch (Exception e) {
      log.warn(String.format("构造带参构造函数%s及其子类构造器失败。", cls.getName()));
    }
    return obj;
  }

  /**
   * 获取无参构造函数
   *
   * @param cls
   * @param <T>
   * @return
   */
  public static <T> T getDefaultConstructor(Class<T> cls) {
    T obj = null;
    // 获取无参的构造函数（仅当带参构造失败时进行）
    try {
      // 无，则获取无参构造函数
      Constructor<T> constructor = cls.getConstructor();
      if (constructor != null) {
        constructor.setAccessible(true);
        obj = constructor.newInstance();
      }
    } catch (Exception e) {
      log.warn(String.format("构造无参构造函数%s及其子类构造器失败。", cls.getName()));
    }
    return obj;
  }

  /**
   * 根据name获取方法
   *
   * @param clazz
   * @param name
   * @return
   */
  public static Method getMethod(Class clazz, String name, boolean hasParam, int length) {
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.getName().equals(name)) {
        // 要求有参数
        if (hasParam) {
          if (method.getParameterTypes().length == length) {
            return method;
          }
          continue;
        }
        return method;
      }
    }
    return null;
  }
}
