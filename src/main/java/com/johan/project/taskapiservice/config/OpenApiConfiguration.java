package com.johan.project.taskapiservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("TaskApiService - API").description(
                "This service is responsible for creating, and removing tasks for users")
                .version("v1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
