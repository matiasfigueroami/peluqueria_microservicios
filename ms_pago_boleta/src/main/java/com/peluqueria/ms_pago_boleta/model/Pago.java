package com.peluqueria.ms_pago_boleta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @NotNull(message = "El id de la cita es obligatorio")
    @Positive(message = "El id de la cita debe ser un número positivo")
    @Column(name = "id_cita", nullable = false)
    private Integer idCita;

    @NotNull(message = "El monto total es obligatorio")
    @Positive(message = "El monto total debe ser un número positivo")
    @Column(name = "monto_total", nullable = false)
    private Integer montoTotal;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 50, message = "El método de pago no puede superar los 50 caracteres")
    @Column(name = "metodo_pago", nullable = false, length = 50)
    private String metodoPago;

    @NotNull(message = "La fecha de pago es obligatoria")
    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;
}
