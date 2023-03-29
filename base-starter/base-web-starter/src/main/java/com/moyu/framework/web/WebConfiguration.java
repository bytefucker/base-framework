package com.moyu.framework.web;

import com.moyu.framework.web.errors.DefaultErrorAttributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * WebConfiguration
 *
 * @author byte_fucker
 * @date 2022/10/26
 */
@Slf4j
@EnableConfigurationProperties(WebConfigurationProperties.class)
@AutoConfigureBefore(value = {WebMvcAutoConfiguration.class})
public class WebConfiguration {


  @Bean
  @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
  public ErrorAttributes errorAttributes() {
    return new DefaultErrorAttributes();
  }

  /**
   * 跨域支持
   *
   * @param properties
   * @return
   */
  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(value = "framework.web.cors.enable", havingValue = "true")
  public CorsFilter corsFilter(WebConfigurationProperties properties) {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(properties.getCors().getAllowedOrigins()); // 设置访问源地址
    config.setAllowedHeaders(properties.getCors().getAllowedHeaders());
    config.setAllowedMethods(properties.getCors().getAllowedHeaders());
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    log.info("注册跨域拦截器成功:{}", properties.getCors());
    return new CorsFilter(source);
  }
}
