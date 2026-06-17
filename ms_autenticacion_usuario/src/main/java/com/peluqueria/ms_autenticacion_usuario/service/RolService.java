package com.peluqueria.ms_autenticacion_usuario.service;
import com.peluqueria.ms_autenticacion_usuario.model.Rol;
import com.peluqueria.ms_autenticacion_usuario.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getRoles(){return rolRepository.findAll();}

    public Optional<Rol> getRol(Integer id){return  rolRepository.findById(id);}

    public Rol saveRol(Rol rolUsuario){return rolRepository.save(rolUsuario);}

    public Rol updateRol(Rol rolUsuario){
        return rolRepository.save(rolUsuario);
    }

    public void deleteRol(Integer id) {
        rolRepository.deleteById(id);
    }
}
