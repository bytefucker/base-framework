package com.moyu.framework.utils.jackson;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Arrays;
import java.util.Map;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


public class ObjectMapperProducer {

  private ObjectMapperProducer() {
  }

  /**
   * 构造通用的objectmapper
   *
   * @return
   */
  @Deprecated
  public static ObjectMapper produce() {
    return produce(null, null, null, null);
  }

  /**
   * 构造通用的objectmapper
   *
   * @param serializerMap
   * @param deserializerMap
   * @param keySerializerMap
   * @param keyDeserializerMap
   * @return
   */
  @Deprecated
  public static ObjectMapper produce(Map<Class, JsonSerializer> serializerMap,
      Map<Class, JsonDeserializer> deserializerMap,
      Map<Class, JsonSerializer> keySerializerMap,
      Map<Class, KeyDeserializer> keyDeserializerMap) {
    Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder = Jackson2ObjectMapperBuilder.json();

    return produceWithCustomizer(jacksonObjectMapperBuilder, builder -> {
      // 增加自定义传入序列化器
      JavaTimeModule javaTimeModule = new JavaTimeModule();
      if (serializerMap != null && !serializerMap.isEmpty()) {
        serializerMap.forEach(javaTimeModule::addSerializer);
      }
      if (deserializerMap != null && !deserializerMap.isEmpty()) {
        deserializerMap.forEach(javaTimeModule::addDeserializer);
      }
      if (keySerializerMap != null && !keySerializerMap.isEmpty()) {
        keySerializerMap.forEach(javaTimeModule::addKeySerializer);
      }
      if (keyDeserializerMap != null && !keyDeserializerMap.isEmpty()) {
        keyDeserializerMap.forEach(javaTimeModule::addKeyDeserializer);
      }
      // 增加可解析类型
      builder.modulesToInstall(javaTimeModule);
    });
  }

  /**
   * 通用生成ObjectMapper方式
   *
   * @param customizers 自定义行为，作为构造ObjectMapper的中间环节
   * @return ObjectMapper
   */
  public static ObjectMapper produceWithCustomizer(
      Jackson2ObjectMapperBuilderCustomizer... customizers) {
    return produceWithCustomizer(Jackson2ObjectMapperBuilder.json(), customizers);
  }

  /**
   * 通用生成ObjectMapper方式
   *
   * @param builder     ObjectMapper构造器
   * @param customizers 自定义行为，作为构造ObjectMapper的中间环节
   * @return ObjectMapper
   */
  public static ObjectMapper produceWithCustomizer(Jackson2ObjectMapperBuilder builder,
      Jackson2ObjectMapperBuilderCustomizer... customizers) {
    // 此步骤代替上述过程进行默认配置
    JacksonCommonGenerate.commonCustom(builder);
    // 传入自定义列表，进行个性化处理，可覆盖上一步默认配置
    JacksonCommonGenerate.customize(builder, Arrays.asList(customizers));
    return builder.build();
  }

  /**
   * 配置已存在的mapper
   *
   * @param objectMapper 已存在的mapper
   * @param customizers  自定义过程
   * @return 配置好的mapper
   */
  public static ObjectMapper configureWithCustomizer(ObjectMapper objectMapper,
      Jackson2ObjectMapperBuilderCustomizer... customizers) {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    // 此步骤代替上述过程进行默认配置
    JacksonCommonGenerate.commonCustom(builder);
    // 传入自定义列表，进行个性化处理，可覆盖上一步默认配置
    JacksonCommonGenerate.customize(builder, Arrays.asList(customizers));
    builder.configure(objectMapper);
    return objectMapper;
  }
}
