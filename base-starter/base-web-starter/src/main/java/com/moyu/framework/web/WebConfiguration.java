package com.moyu.framework.web;

import com.moyu.framework.web.advice.DefaultResponseBodyAdvice;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * WebConfiguration
 *
 * @author byte_fucker
 * @date 2022/10/26
 */
@Slf4j
@EnableConfigurationProperties(WebConfigurationProperties.class)
@AutoConfigureBefore(value = {WebMvcAutoConfiguration.class})
@Configuration
public class WebConfiguration {

  @Bean
  @ConditionalOnProperty(value = "web.enable-rest-wrapper", havingValue = "true")
  public ResponseBodyAdvice restControllerAdvice() {
    log.info("----------------ResponseBodyAdvice注册成功-------------------");
    return new DefaultResponseBodyAdvice();
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(value = "web.cors.enable", havingValue = "true")
  public CorsFilter corsFilter(WebConfigurationProperties properties) {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(properties.getCors().getAllowedOrigins()); // 设置访问源地址
    config.setAllowedHeaders(properties.getCors().getAllowedHeaders());
    config.setAllowedMethods(properties.getCors().getAllowedHeaders());
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    log.info("----------------注册跨域拦截器成功:{}----------------", properties.getCors());
    return new CorsFilter(source);
  }

  @Bean
  public BasicErrorController basicErrorController(ErrorAttributes errorAttributes,
      ObjectProvider<ErrorViewResolver> errorViewResolvers, ServerProperties serverProperties) {
    return new BasicErrorController(errorAttributes, serverProperties.getError(),
        errorViewResolvers.orderedStream().collect(Collectors.toList()));
  }
}
