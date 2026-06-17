package com.peluqueria.ms_agenda_cita.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaEmpleadoDTO {

    private Integer idCita;
    private LocalDateTime fechaHoraInicio;
    private String estado;

    private EmpleadoDTO empleado;
}
