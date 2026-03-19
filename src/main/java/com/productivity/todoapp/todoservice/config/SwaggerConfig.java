package com.productivity.todoapp.todoservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Todo Service API")
                        .description("This API allows users to manage their ToDo tasks, including Create, Update, Delete, and Retrieve operations.")
                        .version("1.0.0"));
    }
}
