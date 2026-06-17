package com.peluqueria.ms_resenas_feedback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "empleadoWebClient")
    public WebClient empleadoWebClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8085")
                .build();
    }

    @Bean(name = "citaWebClient")
    public WebClient citaWebClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8083")
                .build();
    }

}
