package com.peluqueria.ms_agenda_cita.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ServicioDTO {

    private String nombreServicio;
    private Integer precio;
    private String duracionMinutosAprox;
}
