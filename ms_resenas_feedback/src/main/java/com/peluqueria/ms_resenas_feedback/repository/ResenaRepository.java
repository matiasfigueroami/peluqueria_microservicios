package com.peluqueria.ms_resenas_feedback.repository;

import com.peluqueria.ms_resenas_feedback.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {
}
