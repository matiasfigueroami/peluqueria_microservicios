package com.peluqueria.ms_pago_boleta.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaDTO {

    private Integer idCita;
    private LocalDateTime fechaHoraInicio;
    private String estado;
}
