package com.peluqueria.ms_bloqueos_horario.repository;

import com.peluqueria.ms_bloqueos_horario.model.BloqueoHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
// BloqueoHorarioRepository.java
@Repository
public interface BloqueoHorarioRepository extends JpaRepository<BloqueoHorario, Integer> {

    // Busca bloqueos que se solapan con el rango dado para el mismo empleado
    @Query("SELECT b FROM BloqueoHorario b WHERE b.idEmpleado = :idEmpleado " +
            "AND b.fechaHoraInicio < :fin AND b.fechaHoraFin > :inicio")
    List<BloqueoHorario> findSolapados(
            @Param("idEmpleado") Integer idEmpleado,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}
