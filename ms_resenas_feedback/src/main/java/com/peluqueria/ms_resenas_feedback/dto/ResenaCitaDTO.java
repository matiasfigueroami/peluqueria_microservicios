package com.peluqueria.ms_resenas_feedback.dto;

import lombok.Data;

@Data
public class ResenaCitaDTO {

    private Integer idResena;
    private Integer estrella;
    private String comentario;

    private CitaDTO cita;
}
