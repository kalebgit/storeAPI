package com.kal.product_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info()
                .title("StoreAPI - serivico de productos")
                .version("1.0.0")
                .description("Todo lo relacionado a productos de la tienda Ciencias"));

    }
}
