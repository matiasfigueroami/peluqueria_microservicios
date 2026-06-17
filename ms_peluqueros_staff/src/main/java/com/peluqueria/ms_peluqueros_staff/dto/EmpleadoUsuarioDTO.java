package com.peluqueria.ms_peluqueros_staff.dto;

import lombok.Data;

@Data
public class EmpleadoUsuarioDTO {

    private Integer idEmpleado;
    private String pnombre;
    private String snombre;
    private String apPaterno;
    private String apMaterno;

    private UsuarioDTO usuario;
}
