package com.peluqueria.ms_bloqueos_horario.service;

import com.peluqueria.ms_bloqueos_horario.client.EmpleadoClient;
import com.peluqueria.ms_bloqueos_horario.dto.BloqueoHorarioEmpleadoDTO;
import com.peluqueria.ms_bloqueos_horario.dto.EmpleadoDTO;
import com.peluqueria.ms_bloqueos_horario.model.BloqueoHorario;
import com.peluqueria.ms_bloqueos_horario.repository.BloqueoHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloqueoHorarioService {
    @Autowired
    private BloqueoHorarioRepository bloqueoHorarioRepository;

    public List<BloqueoHorario> getBloqueoHorario() {
        return bloqueoHorarioRepository.findAll();
    }

    public Optional<BloqueoHorario> getBloqueoHorario(Integer id) {
        return bloqueoHorarioRepository.findById(id);
    }

    public BloqueoHorario saveBloqueoHorario(BloqueoHorario bloqueoHorario) {
        // Verificar si ya existe un bloqueo solapado para ese empleado
        List<BloqueoHorario> solapados = bloqueoHorarioRepository.findSolapados(
                bloqueoHorario.getIdEmpleado(),
                bloqueoHorario.getFechaHoraInicio(),
                bloqueoHorario.getFechaHoraFin()
        );
        if (!solapados.isEmpty()) {
            throw new RuntimeException(
                    "El empleado ya tiene un bloqueo en ese horario: " +
                            bloqueoHorario.getFechaHoraInicio() + " - " +
                            bloqueoHorario.getFechaHoraFin()
            );
        }
        return bloqueoHorarioRepository.save(bloqueoHorario);
    }

    public BloqueoHorario updateBloqueoHorario(BloqueoHorario bloqueoHorario) {
        return bloqueoHorarioRepository.save(bloqueoHorario);
    }

    public void deleteBloqueoHorario(Integer id) {
        bloqueoHorarioRepository.deleteById(id);
    }

    @Autowired
    private EmpleadoClient empleadoClient;

    public BloqueoHorarioEmpleadoDTO getBloqueoHorarioConEmpleado() {

        Integer idBloqueoHorario = 1;
        Integer idEmpleado = 1;

        BloqueoHorario bloqueoHorario =
                bloqueoHorarioRepository.findById(idBloqueoHorario).orElse(null);

        EmpleadoDTO empleado =
                empleadoClient.obtenerEmpleado(idEmpleado);

        BloqueoHorarioEmpleadoDTO dto =
                new BloqueoHorarioEmpleadoDTO();

        dto.setIdBloqueoHorario(bloqueoHorario.getIdBloqueoHorario());
        dto.setFechaHoraInicio(bloqueoHorario.getFechaHoraInicio());
        dto.setFechaHoraFin(bloqueoHorario.getFechaHoraFin());
        dto.setMotivo(bloqueoHorario.getMotivo());

        dto.setEmpleado(empleado);

        return dto;
    }


}
