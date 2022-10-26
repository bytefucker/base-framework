package com.moyu.framework.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.spi.DocumentationType;

/**
 * SwaggerConfigurationProperties
 *
 * @author yihongzhi
 * @date 2022/8/17
 */
@Data
@ConfigurationProperties("moyu.swagger")
public class SwaggerConfigurationProperties {

  private String title = "moyu";

  private String version = "1.0.0";

  private String description = "";

  private DocumentationType type = DocumentationType.SWAGGER_2;

  private String apiPrefix = "/api/**";

  private Boolean authEnable = false;

}
