package com.peluqueria.ms_resenas_feedback.service;

import com.peluqueria.ms_resenas_feedback.client.CitaClient;
import com.peluqueria.ms_resenas_feedback.client.EmpleadoClient;
import com.peluqueria.ms_resenas_feedback.dto.CitaDTO;
import com.peluqueria.ms_resenas_feedback.dto.EmpleadoDTO;
import com.peluqueria.ms_resenas_feedback.dto.ResenaCitaDTO;
import com.peluqueria.ms_resenas_feedback.dto.ResenaEmpleadoDTO;
import com.peluqueria.ms_resenas_feedback.model.Resena;
import com.peluqueria.ms_resenas_feedback.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena> getResenas(){
        return resenaRepository.findAll();
    }

    public Resena saveResena(Resena resena){
        return resenaRepository.save(resena);
    }

    public Optional<Resena> getResena(Integer id){
        return resenaRepository.findById(id);
    }

    public Resena updateResena(Resena resena){
        return resenaRepository.save(resena);
    }

    public void deleteResena(Integer id){
        resenaRepository.deleteById(id);
    }

    @Autowired
    private EmpleadoClient empleadoClient;

    public ResenaEmpleadoDTO getResenaDeEmpleado(){

        Integer idResena = 1;
        Integer idEmpleado = 1;

        Resena resena =
                resenaRepository.findById(idResena).orElse(null);

        EmpleadoDTO empleado =
                    empleadoClient.obtenerEmpleado(idEmpleado);

        ResenaEmpleadoDTO dto =
                new ResenaEmpleadoDTO();

        dto.setIdResena(resena.getIdResena());
        dto.setEstrella(resena.getEstrella());
        dto.setComentario(resena.getComentario());

        dto.setEmpleado(empleado);

        return dto;
    }

    @Autowired
    private CitaClient citaClient;

    public ResenaCitaDTO getResenaDeCita(){

        Integer idResena = 1;
        Integer idCita = 1;

        Resena resena =
                resenaRepository.findById(idResena).orElse(null);

        CitaDTO cita =
                citaClient.obtenerCita(idCita);

        ResenaCitaDTO dto2 =
                new ResenaCitaDTO();

        dto2.setIdResena(resena.getIdResena());
        dto2.setEstrella(resena.getEstrella());
        dto2.setComentario(resena.getComentario());

        dto2.setCita(cita);

        return dto2;
    }
}
