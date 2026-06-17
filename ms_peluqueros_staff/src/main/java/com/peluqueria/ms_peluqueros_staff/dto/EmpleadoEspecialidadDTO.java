package com.peluqueria.ms_peluqueros_staff.dto;

import lombok.Data;

@Data
public class EmpleadoEspecialidadDTO {

    private Integer idEmpleado;
    private String pnombre;
    private String snombre;
    private String apPaterno;
    private String apMaterno;

    private EspecialidadDTO especialidad;
}
