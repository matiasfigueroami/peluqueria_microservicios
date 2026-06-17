package com.peluqueria.ms_resenas_feedback.dto;

import lombok.Data;

@Data
public class ResenaEmpleadoDTO {

    private Integer idResena;
    private Integer estrella;
    private String comentario;

    private EmpleadoDTO empleado;
}
