package com.peluqueria.ms_clientes_perfil.dto;

import lombok.Data;

@Data
public class ClienteUsuarioDTO {

    private int idCliente;
    private String primerNombre;
    private String apellidoPaterno;
    private String telefono;
    private String notasAlergia;

    private UsuarioDTO usuario;
}
