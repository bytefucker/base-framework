package com.moyu.framework.utils.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import java.util.Objects;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class JacksonCommonGenerate {

  /**
   * 基础库通用jackson配置
   *
   * @param jacksonObjectMapperBuilder 待设置builder
   */
  public static void commonCustom(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
    jacksonObjectMapperBuilder
        .failOnUnknownProperties(false)
        .failOnEmptyBeans(false)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .featuresToEnable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
        .featuresToDisable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
        .featuresToDisable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
  }

  /**
   * 适配Springboot标准生成方式，后期使用ObjectMapper过程中可以注入已有customizers，完成和Spring中一致的配置
   *
   * @param builder     待设置builder
   * @param customizers 自定义处理器
   */
  public static void customize(Jackson2ObjectMapperBuilder builder,
      List<Jackson2ObjectMapperBuilderCustomizer> customizers) {
    if (Objects.nonNull(customizers)) {
      for (Jackson2ObjectMapperBuilderCustomizer customizer : customizers) {
        customizer.customize(builder);
      }
    }
  }
}
