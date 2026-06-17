package com.peluqueria.ms_especialidades_catalogo.repository;

import com.peluqueria.ms_especialidades_catalogo.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
}
