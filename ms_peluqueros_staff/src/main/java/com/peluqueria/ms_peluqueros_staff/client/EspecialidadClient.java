package com.peluqueria.ms_peluqueros_staff.client;

import com.peluqueria.ms_peluqueros_staff.dto.EspecialidadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EspecialidadClient {

    @Autowired
    @Qualifier("especialidadWebClient")
    private WebClient webClient;

    public EspecialidadDTO obtenerEspecialidad(Integer id){

        return webClient
                .get()
                .uri("/api/v1/especialidades/{id}", id)
                .retrieve()
                .bodyToMono(EspecialidadDTO.class)
                .block();
    }
}
