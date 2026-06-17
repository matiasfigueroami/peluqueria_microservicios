package com.peluqueria.ms_notificacion.client;

import com.peluqueria.ms_notificacion.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClienteClient {

    @Autowired
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
