package com.moyu.framework.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig
 *
 * @author yihongzhi
 * @date 2022/8/17
 */
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "${framework.swagger.title}",
        version = "${framework.swagger.version}"
    ),
    security = @SecurityRequirement(name = "token")
)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "token", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class SwaggerConfiguration {

}
