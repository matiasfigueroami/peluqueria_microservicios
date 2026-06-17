package com.peluqueria.ms_agenda_cita.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "clienteWebClient")
    public WebClient clientewebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8084")
                .build();
    }

    @Bean(name = "empleadoWebClient")
    public WebClient empleadowebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8085")
                .build();
    }

    @Bean(name = "servicioWebClient")
    public WebClient servicioWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8088")
                .build();
    }
}
