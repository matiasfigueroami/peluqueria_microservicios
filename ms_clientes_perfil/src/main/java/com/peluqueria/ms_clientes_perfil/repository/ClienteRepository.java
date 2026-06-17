package com.peluqueria.ms_clientes_perfil.repository;

import com.peluqueria.ms_clientes_perfil.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
