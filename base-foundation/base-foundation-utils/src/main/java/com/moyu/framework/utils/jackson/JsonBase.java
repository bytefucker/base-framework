package com.moyu.framework.utils.jackson;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.Assert;

public class JsonBase {

  private final Logger logger = LoggerFactory.getLogger(JsonBase.class);

  private final ObjectMapper mapper;

  /**
   * 默认构造器
   */
  public JsonBase() {
    mapper = ObjectMapperProducer.produceWithCustomizer();
  }

  /**
   * 由开发者提供mapper
   *
   * @param objectMapper mapper读写器
   * @see ObjectMapperProducer
   */
  public JsonBase(ObjectMapper objectMapper) {
    mapper = objectMapper;
  }

  /**
   * 由开发者提供mapper
   *
   * @param builder     构造器
   * @param customizers 自定义构造
   * @see ObjectMapperProducer
   */
  public JsonBase(
      Jackson2ObjectMapperBuilder builder, Jackson2ObjectMapperBuilderCustomizer... customizers) {
    mapper = ObjectMapperProducer.produceWithCustomizer(builder, customizers);
  }

  /**
   * 序列化对象为json格式字符串 如果对象为null,返回"null". 如果集合为空集合,返回"[]". 只要返回null，说明转换出错了
   *
   * @param obj 待序列化对象
   * @return 序列化后的json格式字符串
   */
  @SneakyThrows
  public <T> String toJson(T obj) {
    return mapper.writeValueAsString(obj);
  }

  /**
   * byte[]转换T
   *
   * @param bytes 待反序列化data字节
   * @param clazz 序列化类型
   * @param <T>   序列化类型
   * @return 序列化对象
   */
  @SneakyThrows
  public <T> T toBean(byte[] bytes, Class<T> clazz) {
    Assert.notNull(bytes, "bytes 不能为空");
    return mapper.readValue(bytes, clazz);
  }

  /**
   * 将 JSON 字符串转为 Java 对象 传入"null"，返回值为null
   */
  @SneakyThrows
  public <T> T toBean(String json, Class<T> clazz) {
    Assert.notNull(json, "json 不能为空");
    return mapper.readValue(json, clazz);
  }

  /**
   * json 转 类型list
   *
   * @param jsonString 待序列化字符串
   * @param clazz      序列化类型
   * @param <T>        序列化类型
   * @return 序列化对象
   */
  @SneakyThrows
  public <T> List<T> toBeanList(String jsonString, Class<T> clazz) {
    TypeFactory t = TypeFactory.defaultInstance();
    return mapper.readValue(jsonString, t.constructCollectionType(ArrayList.class, clazz));
  }

  /**
   * byte[] 转 类型list
   *
   * @param bytes 序列化data
   * @param clazz 序列化类型
   * @param <T>   序列化类型
   * @return 序列化对象
   */
  @SneakyThrows
  public <T> List<T> toBeanList(byte[] bytes, Class<T> clazz) {
    TypeFactory t = TypeFactory.defaultInstance();
    return mapper.readValue(bytes, t.constructCollectionType(ArrayList.class, clazz));
  }

  /**
   * json转map
   *
   * @param jsonString json字符串
   * @param clazz      反序列化对象对应class对应具体的实现map接口的map对象
   * @param <T>        方法泛型限制参数类型
   * @return map对象
   */
  @SneakyThrows
  public <T extends Map<String, Object>> T toMap(String jsonString, Class<T> clazz) {
    return mapper.readValue(jsonString, clazz);
  }

  /**
   * bytes转map
   *
   * @param bytes 数据
   * @param clazz 反序列化对象对应class对应具体的实现map接口的map对象
   * @param <T>   方法泛型限制参数类型
   * @return map对象
   */
  @SneakyThrows
  public <T extends Map<String, Object>> T toMap(byte[] bytes, Class<T> clazz) {
    return mapper.readValue(bytes, clazz);
  }

  /**
   * 根据引用类型转换
   *
   * @param content      字符串
   * @param valueTypeRef 引用
   * @param <T>          引用对应的类型转换后数据
   * @return
   */
  @SneakyThrows
  public <T> T readValue(String content, TypeReference<T> valueTypeRef) {
    return mapper.readValue(content, valueTypeRef);
  }

  /**
   * 根据引用类型转换
   *
   * @param bytes        字符数组
   * @param valueTypeRef 引用
   * @param <T>          引用对应的类型转换后数据
   * @return
   */
  @SneakyThrows
  public <T> T readValue(byte[] bytes, TypeReference<T> valueTypeRef) {
    return mapper.readValue(bytes, valueTypeRef);
  }

  /**
   * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.
   *
   * @param pattern 日期格式
   */
  public void setDateFormat(String pattern) {
    if (StringUtils.isNotBlank(pattern)) {
      DateFormat df = new SimpleDateFormat(pattern);
      mapper.setDateFormat(df);
    }
  }

  /**
   * 取出Mapper做进一步的设置或使用其他序列化API.
   *
   * @return 序列化配置对象
   */
  public ObjectMapper getMapper() {
    return mapper;
  }
}
