package com.peluqueria.ms_notificacion.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDTO {

    private String primerNombre;
    private String apellidoPaterno;
}
