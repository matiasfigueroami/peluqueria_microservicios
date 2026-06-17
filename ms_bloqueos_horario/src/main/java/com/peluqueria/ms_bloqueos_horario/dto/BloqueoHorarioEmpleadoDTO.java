package com.peluqueria.ms_bloqueos_horario.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BloqueoHorarioEmpleadoDTO {

    private Integer idBloqueoHorario;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String motivo;

    private EmpleadoDTO empleado;
}
