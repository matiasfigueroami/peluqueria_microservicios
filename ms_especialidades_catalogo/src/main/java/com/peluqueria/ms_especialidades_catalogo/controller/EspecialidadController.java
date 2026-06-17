package com.peluqueria.ms_especialidades_catalogo.controller;

import com.peluqueria.ms_especialidades_catalogo.model.Especialidad;
import com.peluqueria.ms_especialidades_catalogo.service.EspecialidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<Especialidad>> getEspecialidades() {
        return new ResponseEntity<>(especialidadService.getEspecialidades(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getEspecialidad(@PathVariable Integer id) {
        Optional<Especialidad> especialidadOpt = especialidadService.getEspecialidad(id);
        if (especialidadOpt.isPresent()) {
            return new ResponseEntity<>(especialidadOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Especialidad> saveEspecialidad(@Valid @RequestBody Especialidad especialidad) {
        return new ResponseEntity<>(especialidadService.saveEspecialidad(especialidad), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> updateEspecialidad(@PathVariable Integer id,
                                                           @Valid @RequestBody Especialidad especialidad) {
        Optional<Especialidad> especialidadExistente = especialidadService.getEspecialidad(id);
        if (especialidadExistente.isPresent()) {
            especialidad.setIdEspecialidad(id);
            return new ResponseEntity<>(especialidadService.updateEspecialidad(especialidad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable Integer id) {
        Optional<Especialidad> especialidadExistente = especialidadService.getEspecialidad(id);
        if (especialidadExistente.isPresent()) {
            especialidadService.deleteEspecialidad(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
