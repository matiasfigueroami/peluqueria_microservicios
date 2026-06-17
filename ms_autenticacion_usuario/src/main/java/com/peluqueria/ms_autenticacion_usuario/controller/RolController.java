package com.peluqueria.ms_autenticacion_usuario.controller;

import com.peluqueria.ms_autenticacion_usuario.model.Rol;
import com.peluqueria.ms_autenticacion_usuario.service.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> getRoles() {
        return new ResponseEntity<>(rolService.getRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRol(@PathVariable Integer id) {
        Optional<Rol> rolOpt = rolService.getRol(id);
        if (rolOpt.isPresent()) {
            return new ResponseEntity<>(rolOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Rol> saveRol(@Valid @RequestBody Rol rolUsuario) {
        return new ResponseEntity<>(rolService.saveRol(rolUsuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Integer id, @Valid @RequestBody Rol rolUsuario) {
        Optional<Rol> rolExistente = rolService.getRol(id);
        if (rolExistente.isPresent()) {
            rolUsuario.setIdRol(id);
            return new ResponseEntity<>(rolService.updateRol(rolUsuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Integer id) {
        Optional<Rol> rolExistente = rolService.getRol(id);
        if (rolExistente.isPresent()) {
            rolService.deleteRol(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
