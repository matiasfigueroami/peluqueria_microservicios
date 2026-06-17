package com.peluqueria.ms_especialidades_catalogo.service;

import com.peluqueria.ms_especialidades_catalogo.model.Especialidad;
import com.peluqueria.ms_especialidades_catalogo.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Especialidad> getEspecialidades(){
        return especialidadRepository.findAll();
    }

    public Especialidad saveEspecialidad(Especialidad especialidad){
        return especialidadRepository.save(especialidad);
    }

    public Optional<Especialidad> getEspecialidad(Integer id){
        return especialidadRepository.findById(id);
    }

    public Especialidad updateEspecialidad(Especialidad especialidad){
        return especialidadRepository.save(especialidad);
    }

    public void deleteEspecialidad(Integer id){
        especialidadRepository.deleteById(id);
    }
}
