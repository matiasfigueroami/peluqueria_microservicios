package com.peluqueria.ms_servicios_precio.controller;

import com.peluqueria.ms_servicios_precio.model.Servicio;
import com.peluqueria.ms_servicios_precio.service.ServicioService;
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
@RequestMapping("/api/v1/servicios")
@Tag(name = "Servicios", description = "Operaciones relacionadas con los servicios de MS Peluqueria")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @Operation(summary = "Obtiene todos los detalles de los servicios")
    @GetMapping
    public ResponseEntity<List<Servicio>> getServicios() {
        return new ResponseEntity<>(servicioService.getServicios(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de un servicio mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicio(@PathVariable Integer id) {
        Optional<Servicio> servicioOpt = servicioService.getServicio(id);
        if (servicioOpt.isPresent()) {
            return new ResponseEntity<>(servicioOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Agrega un nuevo servicio")
    @PostMapping
    public ResponseEntity<Servicio> saveServicio(@Valid @RequestBody Servicio servicio) {
        return new ResponseEntity<>(servicioService.saveServicio(servicio), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un servicio existente mediante su ID")
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

    @Operation(summary = "Elimina un servicio existente mediante su ID")
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
