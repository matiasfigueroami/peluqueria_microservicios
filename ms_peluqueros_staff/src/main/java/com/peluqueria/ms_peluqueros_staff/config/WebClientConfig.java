package com.peluqueria.ms_peluqueros_staff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "usuarioWebClient")
    public WebClient usuarioWebClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Bean(name = "especialidadWebClient")
    public WebClient especialidadWebClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8087")
                .build();
    }

}
