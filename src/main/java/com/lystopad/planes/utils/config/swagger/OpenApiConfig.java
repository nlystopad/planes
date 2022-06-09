package com.lystopad.planes.utils.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Plane CRUD API")
                        .description("Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                        .version("v0.0.1"));
    }
}
