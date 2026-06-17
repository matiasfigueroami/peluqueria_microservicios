package com.peluqueria.ms_agenda_cita.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;

    @NotNull(message = "El id del cliente es obligatorio")
    @Positive(message = "El id del cliente debe ser un número positivo")
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @NotNull(message = "El id del empleado es obligatorio")
    @Positive(message = "El id del empleado debe ser un número positivo")
    @Column(name = "id_empleado", nullable = false)
    private Integer idEmpleado;

    @NotNull(message = "El id del servicio es obligatorio")
    @Positive(message = "El id del servicio debe ser un número positivo")
    @Column(name = "id_servicio", nullable = false)
    private Integer idServicio;

    @NotNull(message = "La fecha y hora de inicio es obligatoria")
    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50, message = "El estado no puede superar los 50 caracteres")
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
}
