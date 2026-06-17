package com.peluqueria.ms_resenas_feedback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resena")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Integer idResena;

    @NotNull(message = "El id del empleado es obligatorio")
    @Positive(message = "El id del empleado debe ser un número positivo")
    @Column(name = "id_empleado", nullable = false)
    private Integer idEmpleado;

    @NotNull(message = "El id de la cita es obligatorio")
    @Positive(message = "El id de la cita debe ser un número positivo")
    @Column(name = "id_cita", nullable = false)
    private Integer idCita;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1 estrella")
    @Max(value = 5, message = "La calificación máxima es 5 estrellas")
    @Column(name = "estrella", nullable = false)
    private Integer estrella;

    @Size(max = 500, message = "El comentario no puede superar los 500 caracteres")
    @Column(name = "comentario", length = 500)
    private String comentario;
}
