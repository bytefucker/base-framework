package com.moyu.framework.swagger;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.SpringDocConfiguration;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * SwaggerConfig
 *
 * @author yhz
 * @date 2022/8/17
 */
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
@AutoConfigureBefore(value = {SpringDocConfiguration.class})
public class SwaggerConfiguration {

  @Bean
  public OpenApiCustomiser openApiCustomiser(SwaggerConfigurationProperties config) {
    return openApi -> {
      Info info = new Info();
      info.setTitle(config.getTitle());
      info.setDescription(config.getDescription());
      info.setVersion(config.getVersion());
      openApi.setInfo(info);
    };
  }

}
