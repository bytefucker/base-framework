package com.moyu.framework.web;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WebConfigurationProperties
 *
 * @author byte_fucker
 * @date 2022/10/26
 */
@Data
@Component
@ConfigurationProperties("framework.web")
public class WebConfigurationProperties {

  /**
   * 跨域请求支持
   */
  private CorsConfigurationProperties cors = new CorsConfigurationProperties();


  @Data
  public static class CorsConfigurationProperties {

    private Boolean enable = false;
    private List<String> allowedOrigins = new ArrayList<>();
    private List<String> allowedMethods = new ArrayList<>();
    private List<String> allowedHeaders = new ArrayList<>();
    private Long maxAge = 3600L;
  }
}
