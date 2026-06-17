package com.peluqueria.ms_resenas_feedback.client;

import com.peluqueria.ms_resenas_feedback.dto.CitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CitaClient {

    @Autowired
    @Qualifier("citaWebClient")
    private WebClient webClient;

    public CitaDTO obtenerCita(Integer id){

        return webClient
                .get()
                .uri("/api/v1/citas/{id}", id)
                .retrieve()
                .bodyToMono(CitaDTO.class)
                .block();
    }

}
