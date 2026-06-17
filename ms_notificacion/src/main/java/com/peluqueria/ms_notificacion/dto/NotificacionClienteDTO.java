package com.peluqueria.ms_notificacion.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacionClienteDTO {

    private Integer idNotificacion;
    private String mensaje;
    private LocalDateTime fechaEnvio;

    private ClienteDTO cliente;
}
