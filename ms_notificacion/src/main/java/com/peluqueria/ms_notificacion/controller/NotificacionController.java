package com.peluqueria.ms_notificacion.controller;

import com.peluqueria.ms_notificacion.dto.NotificacionClienteDTO;
import com.peluqueria.ms_notificacion.model.Notificacion;
import com.peluqueria.ms_notificacion.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Tag(name = "Notificaciones", description = "Operaciones relacionadas con las notificaciones de MS Peluqueria")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Operation(summary = "Obtiene todos los detalles de las notificaciones")
    @GetMapping
    public ResponseEntity<List<Notificacion>> getNotificaciones() {
        return new ResponseEntity<>(notificacionService.getNotificaciones(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de una notificacion mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacion(@PathVariable Integer id) {
        Optional<Notificacion> notificacionOpt = notificacionService.getNotificacion(id);
        if (notificacionOpt.isPresent()) {
            return new ResponseEntity<>(notificacionOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Agrega una nueva notificacion")
    @PostMapping
    public ResponseEntity<Notificacion> saveNotificacion(@Valid @RequestBody Notificacion notificacion) {
        return new ResponseEntity<>(notificacionService.saveNotificacion(notificacion), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica una notificacion mediante su ID")
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

    @Operation(summary = "Elimina una notificacion mediante si ID")
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

    @Operation(summary = "Obtiene todos los detalles de una notificacion con el cliente")
    @GetMapping("/clientes")
    public ResponseEntity<NotificacionClienteDTO> getNotificacionConCliente() {
        return new ResponseEntity<>(notificacionService.getNotificacionConCliente(), HttpStatus.OK);
    }
}
