package com.peluqueria.ms_servicios_precio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestion de servicios y categorias")
                        .version("1.0")
                        .description("Microservicio para la gestion " +
                                "de servicios y categorias")
                );
    }
}
