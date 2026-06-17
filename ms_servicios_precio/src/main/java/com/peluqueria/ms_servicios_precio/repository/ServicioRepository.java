package com.peluqueria.ms_servicios_precio.repository;

import com.peluqueria.ms_servicios_precio.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
