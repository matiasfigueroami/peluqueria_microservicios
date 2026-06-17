package com.peluqueria.ms_pago_boleta.repository;

import com.peluqueria.ms_pago_boleta.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
