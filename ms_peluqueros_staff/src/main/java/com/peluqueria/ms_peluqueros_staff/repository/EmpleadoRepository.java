package com.peluqueria.ms_peluqueros_staff.repository;

import com.peluqueria.ms_peluqueros_staff.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
