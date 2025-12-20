package com.university.java_lecture_3.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "University API",
                description = "API управления событиями, пользователями и группами",
                version = "1.0.0"
        )
)
public class OpenApiConfig {
}
