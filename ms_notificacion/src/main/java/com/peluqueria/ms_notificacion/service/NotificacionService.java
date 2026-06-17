package com.peluqueria.ms_notificacion.service;

import com.peluqueria.ms_notificacion.client.ClienteClient;
import com.peluqueria.ms_notificacion.dto.ClienteDTO;
import com.peluqueria.ms_notificacion.dto.NotificacionClienteDTO;
import com.peluqueria.ms_notificacion.model.Notificacion;
import com.peluqueria.ms_notificacion.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> getNotificaciones(){return notificacionRepository.findAll();}

    public Optional<Notificacion> getNotificacion(Integer id){return  notificacionRepository.findById(id);}

    public Notificacion saveNotificacion(Notificacion notificacion){return notificacionRepository.save(notificacion);}

    public Notificacion updateNotificacion(Notificacion notificacion){
        return notificacionRepository.save(notificacion);
    }
    public void deleteNotificacion(Integer id) {
        notificacionRepository.deleteById(id);
    }


    @Autowired
    private ClienteClient clienteClient;

    public NotificacionClienteDTO getNotificacionConCliente() {

        Integer idNotificacion = 1;
        Integer idCliente = 1;

        Notificacion notificacion =
                notificacionRepository.findById(idNotificacion).orElse(null);

        ClienteDTO cliente =
                clienteClient.obtenerCliente(idCliente);

        NotificacionClienteDTO dto =
                new NotificacionClienteDTO();

        dto.setIdNotificacion(notificacion.getIdNotificacion());
        dto.setMensaje(notificacion.getMensaje());
        dto.setFechaEnvio(notificacion.getFechaEnvio());

        dto.setCliente(cliente);

        return dto;
    }
}
