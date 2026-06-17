package com.peluqueria.ms_agenda_cita.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaServicioDTO {

    private Integer idCita;
    private LocalDateTime fechaHoraInicio;
    private String estado;

    private ServicioDTO servicio;
}
