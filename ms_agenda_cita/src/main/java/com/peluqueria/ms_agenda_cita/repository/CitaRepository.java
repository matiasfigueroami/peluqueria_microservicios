package com.peluqueria.ms_agenda_cita.repository;

import com.peluqueria.ms_agenda_cita.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {


    @Query("SELECT c FROM Cita c WHERE c.idEmpleado = :idEmpleado " +
            "AND c.fechaHoraInicio = :fechaHoraInicio " + "AND c.estado != 'Cancelada'")
    List<Cita> findCitasSolapadas(
            @Param("idEmpleado") Integer idEmpleado,
            @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio
    );

    @Query(value = "SELECT COUNT(*) FROM ms_bloqueo_horario.bloqueo_horario " +
            "WHERE id_empleado = :idEmpleado " +
            "AND :fechaHora >= fecha_hora_inicio " +
            "AND :fechaHora < fecha_hora_fin",
            nativeQuery = true)
    int existeBloqueoEnHorario(
            @Param("idEmpleado") Integer idEmpleado,
            @Param("fechaHora") LocalDateTime fechaHora
    );
}
