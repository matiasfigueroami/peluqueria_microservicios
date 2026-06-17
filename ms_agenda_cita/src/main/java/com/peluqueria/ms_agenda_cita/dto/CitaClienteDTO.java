package com.peluqueria.ms_agenda_cita.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaClienteDTO {

    private Integer idCita;
    private LocalDateTime fechaHoraInicio;
    private String estado;

    private ClienteDTO cliente;
}
