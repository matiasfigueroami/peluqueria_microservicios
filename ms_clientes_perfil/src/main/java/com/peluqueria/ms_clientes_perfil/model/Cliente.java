package com.peluqueria.ms_clientes_perfil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @NotNull(message = "El id del usuario es obligatorio")
    @Positive(message = "El id del usuario debe ser un número positivo")
    @Column(name="id_usuario", nullable = false)
    private Integer idUsuario;

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(max = 60, message = "El nombre no puede superar los 60 caracteres")
    @Column(name = "primer_nombre", nullable = false, length = 60)
    private String primerNombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 60, message = "El apellido no puede superar los 60 caracteres")
    @Column(name = "apellido_paterno", nullable = false, length = 60)
    private String apellidoPaterno;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    @Column(nullable = false, length = 20)
    private String telefono;

    @Size(max = 255, message = "Las notas de alergia no pueden superar los 255 caracteres")
    @Column(name = "notas_alergia", length = 255)
    private String notasAlergia;
}
