package com.peluqueria.ms_notificacion.repository;

import com.peluqueria.ms_notificacion.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
}
