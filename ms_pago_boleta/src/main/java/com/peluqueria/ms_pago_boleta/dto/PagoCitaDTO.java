package com.peluqueria.ms_pago_boleta.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagoCitaDTO {

    private Integer idPago;
    private Integer montoTotal;
    private String metodoPago;
    private LocalDateTime fechaPago;

    private CitaDTO cita;
}
