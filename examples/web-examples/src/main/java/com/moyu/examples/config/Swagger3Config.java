package com.moyu.examples.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger3Config
 *
 * @author yhz
 * @date 2023/3/28
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "demo",
        version = "1.0"
    ),
    security = @SecurityRequirement(name = "token")
)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "token", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class Swagger3Config {

}
