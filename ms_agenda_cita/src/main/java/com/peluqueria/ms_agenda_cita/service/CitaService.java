package com.peluqueria.ms_agenda_cita.service;

import com.peluqueria.ms_agenda_cita.client.ClienteClient;
import com.peluqueria.ms_agenda_cita.client.EmpleadoClient;
import com.peluqueria.ms_agenda_cita.client.ServicioClient;
import com.peluqueria.ms_agenda_cita.dto.*;
import com.peluqueria.ms_agenda_cita.model.Cita;
import com.peluqueria.ms_agenda_cita.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getCitas(){return citaRepository.findAll();}

    public Optional<Cita> getCita(Integer id){return  citaRepository.findById(id);}

    public Cita saveCita(Cita cita) {

        // Validar citas solapadas
        List<Cita> citasSolapadas = citaRepository.findCitasSolapadas(
                cita.getIdEmpleado(),
                cita.getFechaHoraInicio()
        );
        if (!citasSolapadas.isEmpty()) {
            throw new RuntimeException(
                    "El empleado ya tiene una cita agendada para: " + cita.getFechaHoraInicio()
            );
        }

        int tieneBloqueo = citaRepository.existeBloqueoEnHorario(
                cita.getIdEmpleado(),
                cita.getFechaHoraInicio()
        );
        if (tieneBloqueo > 0) {
            throw new RuntimeException(
                    "El empleado tiene un bloqueo de horario en: " + cita.getFechaHoraInicio()
            );
        }

        return citaRepository.save(cita);
    }

    public Cita updateCita(Cita cita){
        return citaRepository.save(cita);
    }
    public void deleteCita(Integer id) {
        citaRepository.deleteById(id);
    }


    @Autowired
    private ClienteClient clienteClient;

    public CitaClienteDTO getCitaConCliente() {

        Integer idCita = 1;
        Integer idCliente = 1;

        Cita cita =
                citaRepository.findById(idCita).orElse(null);

        ClienteDTO cliente =
                clienteClient.obtenerCliente(idCliente);

        CitaClienteDTO dto =
                new CitaClienteDTO();

        dto.setIdCita(cita.getIdCita());
        dto.setFechaHoraInicio(cita.getFechaHoraInicio());
        dto.setEstado(cita.getEstado());

        dto.setCliente(cliente);

        return dto;
    }

    @Autowired
    private EmpleadoClient empleadoClient;

    public CitaEmpleadoDTO getCitaConEmpleado() {

        Integer idCita = 1;
        Integer idEmpleado = 1;

        Cita cita =
                citaRepository.findById(idCita).orElse(null);

        EmpleadoDTO empleado =
                empleadoClient.obtenerEmpleado(idEmpleado);

        CitaEmpleadoDTO dto2 =
                new CitaEmpleadoDTO();

        dto2.setIdCita(cita.getIdCita());
        dto2.setFechaHoraInicio(cita.getFechaHoraInicio());
        dto2.setEstado(cita.getEstado());

        dto2.setEmpleado(empleado);

        return dto2;
    }

    @Autowired
    private ServicioClient servicioClient;

    public CitaServicioDTO getCitaConServicio() {

        Integer idCita = 1;
        Integer idServicio = 1;

        Cita cita =
                citaRepository.findById(idCita).orElse(null);

        ServicioDTO servicio =
                servicioClient.obtenerServicio(idServicio);

        CitaServicioDTO dto3 =
                new CitaServicioDTO();

        dto3.setIdCita(cita.getIdCita());
        dto3.setFechaHoraInicio(cita.getFechaHoraInicio());
        dto3.setEstado(cita.getEstado());

        dto3.setServicio(servicio);

        return dto3;
    }

}
