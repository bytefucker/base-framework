package com.moyu.framework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moyu.framework.utils.jackson.JsonBase;
import java.util.List;
import java.util.Map;

/**
 * JSON 操作工具类
 */
public final class JsonUtil {

  private static final JsonBase JSON_BASE = new JsonBase();

  /**
   * 无扩展性，仅通过静态方法对外提供服务
   */
  private JsonUtil() {
  }

  /**
   * 序列化对象为json格式字符串 如果对象为null,返回"null". 如果集合为空集合,返回"[]". 只要返回null，说明转换出错了
   *
   * @param obj 待序列化对象
   * @return 序列化后的json格式字符串
   */
  public static <T> String toJson(T obj) {
    return JSON_BASE.toJson(obj);
  }

  /**
   * byte[]转换T
   *
   * @param bytes 序列化data
   * @param clazz 序列化类型
   * @param <T>   序列化类型
   * @return 序列化对象
   */
  public static <T> T toBean(byte[] bytes, Class<T> clazz) {
    return JSON_BASE.toBean(bytes, clazz);
  }

  /**
   * 将 JSON 字符串转为 Java 对象 传入"null"，返回值为null
   */
  public static <T> T toBean(String json, Class<T> clazz) {
    return JSON_BASE.toBean(json, clazz);
  }

  /**
   * json 转 类型list
   *
   * @param jsonString 序列化字符串
   * @param clazz      序列化类型
   * @param <T>        序列化类型
   * @return 序列化对象
   */
  public static <T> List<T> toBeanList(String jsonString, Class<T> clazz) {
    return JSON_BASE.toBeanList(jsonString, clazz);
  }

  /**
   * byte[] 转 类型list
   *
   * @param bytes 序列化data
   * @param clazz 序列化类型
   * @param <T>   序列化类型
   * @return 序列化对象
   */
  public static <T> List<T> toBeanList(byte[] bytes, Class<T> clazz) {
    return JSON_BASE.toBeanList(bytes, clazz);
  }

  /**
   * json转map
   *
   * @param jsonString json字符串
   * @param clazz      反序列化对象对应class对应具体的实现map接口的map对象
   * @param <T>        方法泛型限制参数类型
   * @return map对象
   */
  public static <T extends Map<String, Object>> T toMap(String jsonString, Class<T> clazz) {
    return JSON_BASE.toMap(jsonString, clazz);
  }

  /**
   * bytes转map
   *
   * @param bytes 数据
   * @param clazz 反序列化对象对应class对应具体的实现map接口的map对象
   * @param <T>   方法泛型限制参数类型
   * @return map对象
   */
  public static <T extends Map<String, Object>> T toMap(byte[] bytes, Class<T> clazz) {
    return JSON_BASE.toMap(bytes, clazz);
  }

  /**
   * 根据引用类型转换
   *
   * @param content      字符串
   * @param valueTypeRef 引用
   * @param <T>          引用对应的类型转换后数据
   * @return
   */
  public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
    return JSON_BASE.readValue(content, valueTypeRef);
  }

  /**
   * 根据引用类型转换
   *
   * @param bytes        字符数组
   * @param valueTypeRef 引用
   * @param <T>          引用对应的类型转换后数据
   * @return
   */
  public static <T> T readValue(byte[] bytes, TypeReference<T> valueTypeRef) {
    return JSON_BASE.readValue(bytes, valueTypeRef);
  }

  /**
   * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.
   *
   * @param pattern 日期格式
   */
  public static void setDateFormat(String pattern) {
    JSON_BASE.setDateFormat(pattern);
  }

  /**
   * 取出Mapper做进一步的设置或使用其他序列化API.
   *
   * @return 序列化配置对象
   */
  public static ObjectMapper getMapper() {
    return JSON_BASE.getMapper();
  }

}

