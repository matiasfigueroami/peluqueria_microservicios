package com.peluqueria.ms_notificacion.controller;

import com.peluqueria.ms_notificacion.dto.NotificacionClienteDTO;
import com.peluqueria.ms_notificacion.model.Notificacion;
import com.peluqueria.ms_notificacion.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> getNotificaciones() {
        return new ResponseEntity<>(notificacionService.getNotificaciones(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacion(@PathVariable Integer id) {
        Optional<Notificacion> notificacionOpt = notificacionService.getNotificacion(id);
        if (notificacionOpt.isPresent()) {
            return new ResponseEntity<>(notificacionOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Notificacion> saveNotificacion(@Valid @RequestBody Notificacion notificacion) {
        return new ResponseEntity<>(notificacionService.saveNotificacion(notificacion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> updateNotificacion(@PathVariable Integer id,
                                                           @Valid @RequestBody Notificacion notificacion) {
        Optional<Notificacion> notificacionExistente = notificacionService.getNotificacion(id);
        if (notificacionExistente.isPresent()) {
            notificacion.setIdNotificacion(id);
            return new ResponseEntity<>(notificacionService.updateNotificacion(notificacion), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Integer id) {
        Optional<Notificacion> notificacionExistente = notificacionService.getNotificacion(id);
        if (notificacionExistente.isPresent()) {
            notificacionService.deleteNotificacion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity<NotificacionClienteDTO> getNotificacionConCliente() {
        return new ResponseEntity<>(notificacionService.getNotificacionConCliente(), HttpStatus.OK);
    }
}
