package com.grupo03.Lambda_Voyage.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Best Travel API",
                version = "1.0",
                description = "Documentation for endpoints in LambdaVoyage")
)
public class OpenApiConfig {
}
