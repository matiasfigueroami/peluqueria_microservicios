package com.peluqueria.ms_autenticacion_usuario.repository;

import com.peluqueria.ms_autenticacion_usuario.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
