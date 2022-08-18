package com.moyu.framework.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 本地变量存储工具类
 */
public final class ThreadLocalUtil {

  private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal
      .withInitial(HashMap::new);

  public static void unload() {
    THREAD_LOCAL.remove();
  }

  public static void setObjectToThreadLocal(String key, Object value) {
    Map<String, Object> map = THREAD_LOCAL.get();

    if (map == null) {
      map = new LinkedHashMap<>();
      THREAD_LOCAL.set(map);
    }

    map.put(key, value);
  }

  public static Object getObjectFromThreadLocal(String key) {
    Map<String, Object> map = THREAD_LOCAL.get();

    if (map == null) {
      return null;
    }

    return map.get(key);
  }

  public static Object removeObjectFromThreadLocal(String key) {
    Map<String, Object> map = THREAD_LOCAL.get();

    if (map == null) {
      return null;
    }
    Object rmObj = map.remove(key);
    if (map.size() == 0) {
      THREAD_LOCAL.remove();
    }
    return rmObj;
  }
}