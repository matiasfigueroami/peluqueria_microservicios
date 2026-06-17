package com.peluqueria.ms_servicios_precio.service;

import com.peluqueria.ms_servicios_precio.model.Servicio;
import com.peluqueria.ms_servicios_precio.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> getServicios(){
        return servicioRepository.findAll();
    }

    public Servicio saveServicio(Servicio servicio){
        return servicioRepository.save(servicio);
    }

    public Optional<Servicio> getServicio(Integer id){
        return servicioRepository.findById(id);
    }

    public Servicio updateServicio(Servicio servicio){
        return servicioRepository.save(servicio);
    }

    public void deleteServicio(Integer id){
        servicioRepository.deleteById(id);
    }
}
