package com.peluqueria.ms_servicios_precio.controller;

import com.peluqueria.ms_servicios_precio.model.Servicio;
import com.peluqueria.ms_servicios_precio.service.ServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> getServicios() {
        return new ResponseEntity<>(servicioService.getServicios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicio(@PathVariable Integer id) {
        Optional<Servicio> servicioOpt = servicioService.getServicio(id);
        if (servicioOpt.isPresent()) {
            return new ResponseEntity<>(servicioOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Servicio> saveServicio(@Valid @RequestBody Servicio servicio) {
        return new ResponseEntity<>(servicioService.saveServicio(servicio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Integer id,
                                                   @Valid @RequestBody Servicio servicio) {
        Optional<Servicio> servicioExistente = servicioService.getServicio(id);
        if (servicioExistente.isPresent()) {
            servicio.setIdServicio(id);
            return new ResponseEntity<>(servicioService.updateServicio(servicio), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Integer id) {
        Optional<Servicio> servicioExistente = servicioService.getServicio(id);
        if (servicioExistente.isPresent()) {
            servicioService.deleteServicio(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
