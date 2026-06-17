package com.peluqueria.ms_notificacion.model;

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
@Table(name="historial_notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;

    @NotNull(message = "El id del cliente es obligatorio")
    @Positive(message = "El id del cliente debe ser un número positivo")
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 255, message = "El mensaje no puede superar los 255 caracteres")
    @Column(nullable = false)
    private String mensaje;

    @NotNull(message = "La fecha de envío es obligatoria")
    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;
}
