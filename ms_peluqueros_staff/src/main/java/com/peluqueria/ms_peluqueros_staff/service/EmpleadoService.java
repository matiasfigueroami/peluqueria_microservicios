package com.peluqueria.ms_peluqueros_staff.service;

import com.peluqueria.ms_peluqueros_staff.client.EspecialidadClient;
import com.peluqueria.ms_peluqueros_staff.client.UsuarioClient;
import com.peluqueria.ms_peluqueros_staff.dto.EmpleadoEspecialidadDTO;
import com.peluqueria.ms_peluqueros_staff.dto.EmpleadoUsuarioDTO;
import com.peluqueria.ms_peluqueros_staff.dto.EspecialidadDTO;
import com.peluqueria.ms_peluqueros_staff.dto.UsuarioDTO;
import com.peluqueria.ms_peluqueros_staff.model.Empleado;
import com.peluqueria.ms_peluqueros_staff.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getEmpleados(){
        return empleadoRepository.findAll();
    }

    public Empleado saveEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> getEmpleado(Integer id){
        return empleadoRepository.findById(id);
    }

    public Empleado updateEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public void deleteEmpleado(Integer id){
        empleadoRepository.deleteById(id);
    }


    @Autowired
    private UsuarioClient usuarioClient;

    public EmpleadoUsuarioDTO getEmpleadoConUsuario(){

        Integer idEmpleado = 1;
        Integer idUsuario = 1;

        Empleado empleado =
                empleadoRepository.findById(idEmpleado).orElse(null);

        UsuarioDTO usuario =
                usuarioClient.obtenerUsuario(idUsuario);

        EmpleadoUsuarioDTO dto =
                new EmpleadoUsuarioDTO();

        dto.setIdEmpleado(empleado.getIdEmpleado());
        dto.setPnombre(empleado.getPnombre());
        dto.setSnombre(empleado.getSnombre());
        dto.setApPaterno(empleado.getApPaterno());
        dto.setApMaterno(empleado.getApMaterno());

        dto.setUsuario(usuario);

        return dto;
    }

    @Autowired
    private EspecialidadClient especialidadClient;
    public EmpleadoEspecialidadDTO getEmpleadoConEspecialidad(){

        Integer idEmpleado = 1;
        Integer idEspecialidad = 1;

        Empleado empleado =
                empleadoRepository.findById(idEmpleado).orElse(null);

        EspecialidadDTO especialidad =
                especialidadClient.obtenerEspecialidad(idEspecialidad);

        EmpleadoEspecialidadDTO dto2 =
                new EmpleadoEspecialidadDTO();

        dto2.setIdEmpleado(empleado.getIdEmpleado());
        dto2.setPnombre(empleado.getPnombre());
        dto2.setSnombre(empleado.getSnombre());
        dto2.setApPaterno(empleado.getApPaterno());
        dto2.setApMaterno(empleado.getApMaterno());

        dto2.setEspecialidad(especialidad);

        return dto2;
    }

}
