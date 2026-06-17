package com.peluqueria.ms_bloqueos_horario.controller;

import com.peluqueria.ms_bloqueos_horario.dto.BloqueoHorarioEmpleadoDTO;
import com.peluqueria.ms_bloqueos_horario.model.BloqueoHorario;
import com.peluqueria.ms_bloqueos_horario.service.BloqueoHorarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bloqueoHorarios")
public class BloqueoHorarioController {

    @Autowired
    private BloqueoHorarioService bloqueoHorarioService;

    @GetMapping
    public ResponseEntity<List<BloqueoHorario>> getBloqueoHorario() {
        return new ResponseEntity<>(bloqueoHorarioService.getBloqueoHorario(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloqueoHorario> getBloqueoHorario(@PathVariable Integer id) {
        Optional<BloqueoHorario> bloqueoOpt = bloqueoHorarioService.getBloqueoHorario(id);
        if (bloqueoOpt.isPresent()) {
            return new ResponseEntity<>(bloqueoOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveBloqueoHorario(@RequestBody BloqueoHorario bloqueoHorario) {
        try {
            BloqueoHorario saved = bloqueoHorarioService.saveBloqueoHorario(bloqueoHorario);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloqueoHorario> updateBloqueoHorario(@PathVariable Integer id,
                                                               @Valid @RequestBody BloqueoHorario bloqueoHorario) {
        Optional<BloqueoHorario> bloqueoExistente = bloqueoHorarioService.getBloqueoHorario(id);
        if (bloqueoExistente.isPresent()) {
            bloqueoHorario.setIdBloqueoHorario(id);
            return new ResponseEntity<>(bloqueoHorarioService.updateBloqueoHorario(bloqueoHorario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloqueoHorario(@PathVariable Integer id) {
        Optional<BloqueoHorario> bloqueoExistente = bloqueoHorarioService.getBloqueoHorario(id);
        if (bloqueoExistente.isPresent()) {
            bloqueoHorarioService.deleteBloqueoHorario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/empleados")
    public ResponseEntity<BloqueoHorarioEmpleadoDTO> getBloqueoHorarioConEmpleado() {
        return new ResponseEntity<>(bloqueoHorarioService.getBloqueoHorarioConEmpleado(), HttpStatus.OK);
    }
}
