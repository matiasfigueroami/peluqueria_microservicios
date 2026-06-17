package com.peluqueria.ms_clientes_perfil.client;

import com.peluqueria.ms_clientes_perfil.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UsuarioClient {

    @Autowired
    private WebClient webClient;

    public UsuarioDTO obtenerUsuario(Integer id) {

        return webClient
                .get()
                .uri("/api/v1/usuarios/{id}", id)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
    }

}
