package com.peluqueria.ms_agenda_cita.client;

import com.peluqueria.ms_agenda_cita.dto.EmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmpleadoClient {

    @Autowired
    @Qualifier("empleadoWebClient")
    private WebClient webClient;

    public EmpleadoDTO obtenerEmpleado(Integer id) {

        return webClient
                .get()
                .uri("/api/v1/empleados/{id}", id)
                .retrieve()
                .bodyToMono(EmpleadoDTO.class)
                .block();
    }
}
