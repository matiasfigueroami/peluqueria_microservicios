package com.peluqueria.ms_agenda_cita.client;

import com.peluqueria.ms_agenda_cita.dto.ServicioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServicioClient {

    @Autowired
    @Qualifier("servicioWebClient")
    private WebClient webClient;

    public ServicioDTO obtenerServicio(Integer id) {

        return webClient
                .get()
                .uri("/api/v1/servicios/{id}", id)
                .retrieve()
                .bodyToMono(ServicioDTO.class)
                .block();
    }
}
