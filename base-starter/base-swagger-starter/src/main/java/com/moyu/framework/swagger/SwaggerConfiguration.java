package com.moyu.framework.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import java.util.List;
import java.util.Map;
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
      SecurityRequirement requirement = new SecurityRequirement();
      requirement.addList("token");
      openApi.setSecurity(List.of(requirement));

      SecurityScheme securityScheme = new SecurityScheme();
      securityScheme.setIn(In.HEADER);
      securityScheme.setType(Type.HTTP);
      securityScheme.setName("token");
      securityScheme.setScheme("bearer");
      Components components = new Components();
      components.setSecuritySchemes(Map.of("token", securityScheme));
      openApi.setComponents(components);

    };
  }
}
