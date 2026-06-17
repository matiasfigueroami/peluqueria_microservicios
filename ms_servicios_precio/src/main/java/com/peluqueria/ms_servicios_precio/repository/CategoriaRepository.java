package com.peluqueria.ms_servicios_precio.repository;

import com.peluqueria.ms_servicios_precio.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
