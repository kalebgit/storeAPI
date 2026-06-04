package com.kal.invoice_service.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    // Registrar OkHttpClient explícitamente para que Feign use PATCH correctamente
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
