package com.peluqueria.ms_peluqueros_staff.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @NotNull (message = "El ID de usuario no puede ser nulo")
    @Positive(message = "El ID de usuario debe ser un número positivo")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotBlank(message = "El primer nombre no puede estar vacío")
    @Size(max = 50, message = "El primer nombre no puede exceder los 50 caracteres")
    @Column(name = "pnombre", nullable = false, length = 50)
    private String pnombre;

    @NotBlank(message = "El segundo nombre no puede estar vacío")
    @Size(max = 50, message = "El segundo nombre no puede exceder los 50 caracteres")
    @Column(name = "snombre", nullable = false, length = 50)
    private String snombre;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    @Size(max = 50, message = "El apellido paterno no puede exceder los 50 caracteres")
    @Column(name = "ap_paterno", nullable = false, length = 50)
    private String apPaterno;

    @NotBlank(message = "El apellido materno no puede estar vacío")
    @Size(max = 50, message = "El apellido materno no puede exceder los 50 caracteres")
    @Column(name = "ap_materno", nullable = false, length = 50)
    private String apMaterno;

    @NotNull(message = "El ID de especialidad no puede ser nulo")
    @Positive(message = "El ID de especialidad debe ser un número positivo")
    @Column(name = "id_especialidad", nullable = false)
    private Integer idEspecialidad;


}
