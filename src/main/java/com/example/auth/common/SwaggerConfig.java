package com.example.auth.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        String securitySchemeName = "JWT";

        Info info = new Info()
                .title("Auth API Docs")
                .description("과제테스트 API Docs 입니다.");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(
                        new SecurityRequirement().addList(securitySchemeName)
                )
                .components(
                        new Components().addSecuritySchemes(
                                securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat(securitySchemeName)
                        )
                );
    }

}
