package com.moyu.framework.swagger;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig
 *
 * @author yihongzhi
 * @date 2022/8/17
 */
@EnableOpenApi
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
@Configuration
public class SwaggerConfiguration {

  /**
   * 设置swagger属性
   *
   * @param properties
   * @return
   */
  @Bean
  @ConditionalOnMissingBean
  public Docket docket(SwaggerConfigurationProperties properties) {
    ApiInfo apiInfo = new ApiInfo(properties.getTitle(), properties.getDescription(),
        properties.getVersion(), "", DEFAULT_CONTACT, "", "", new ArrayList());
    Docket docket = new Docket(properties.getType())
        .apiInfo(apiInfo)
        .select()
        .paths(PathSelectors.ant(properties.getApiPrefix()))
        .build();
    //开启权限验证
    if (properties.getAuthEnable()) {
      docket = docket.securityContexts(Arrays.asList(securityContext()))
          .securitySchemes(Arrays.asList(apiKey()));
    }
    return docket;
  }


  private ApiKey apiKey() {
    return new ApiKey("Authorization", "Authorization", In.HEADER.name());
  }


  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth()).build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope[] scopes = new AuthorizationScope[]{
        new AuthorizationScope("global", "accessEverything")
    };
    return Arrays.asList(new SecurityReference("Authorization", scopes));
  }
}
