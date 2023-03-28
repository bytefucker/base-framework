package com.moyu.framework.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SwaggerConfigurationProperties
 *
 * @author yihongzhi
 * @date 2022/8/17
 */
@Data
@ConfigurationProperties("framework.swagger")
public class SwaggerConfigurationProperties {

  private String title = "demo";

  private String version = "1.0.0";

  private String description = "";
}
