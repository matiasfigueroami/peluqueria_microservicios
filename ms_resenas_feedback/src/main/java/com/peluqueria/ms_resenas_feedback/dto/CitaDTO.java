package com.peluqueria.ms_resenas_feedback.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaDTO {

    private Integer idCita;
    private LocalDateTime fechaHoraInicio;
    private String estado;
}
