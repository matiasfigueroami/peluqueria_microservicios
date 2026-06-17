package com.peluqueria.ms_agenda_cita.client;

import com.peluqueria.ms_agenda_cita.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClienteClient {

    @Autowired
    @Qualifier("clienteWebClient")
    private WebClient webClient;

    public ClienteDTO obtenerCliente(Integer id) {

        return webClient
                .get()
                .uri("/api/v1/clientes/{id}", id)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block();
    }
}
