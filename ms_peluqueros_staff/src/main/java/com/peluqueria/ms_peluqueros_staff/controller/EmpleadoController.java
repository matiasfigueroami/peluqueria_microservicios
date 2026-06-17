package com.peluqueria.ms_peluqueros_staff.controller;

import com.peluqueria.ms_peluqueros_staff.dto.EmpleadoEspecialidadDTO;
import com.peluqueria.ms_peluqueros_staff.dto.EmpleadoUsuarioDTO;
import com.peluqueria.ms_peluqueros_staff.model.Empleado;
import com.peluqueria.ms_peluqueros_staff.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // 200 OK
    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {
        return ResponseEntity.ok(empleadoService.getEmpleados());
    }

    // 200 OK o 404 NOT FOUND
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable Integer id) {
        Optional<Empleado> empleadoOpt = empleadoService.getEmpleado(id);

        if (empleadoOpt.isPresent()) {
            return ResponseEntity.ok(empleadoOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 201 CREATED (Se agrega @Valid para validar el body)
    @PostMapping
    public ResponseEntity<Empleado> saveEmpleado(@Valid @RequestBody Empleado empleado) {
        Empleado empleadoGuardado = empleadoService.saveEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado);
    }

    // 200 OK o 404 NOT FOUND (Se agrega @Valid)
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Integer id, @Valid @RequestBody Empleado empleado) {
        Optional<Empleado> empleadoExistente = empleadoService.getEmpleado(id);

        if (empleadoExistente.isPresent()) {
            empleado.setIdEmpleado(id);
            Empleado empleadoActualizado = empleadoService.updateEmpleado(empleado);
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 200 OK o 404 NOT FOUND
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Integer id) {
        Optional<Empleado> empleadoExistente = empleadoService.getEmpleado(id);

        if (empleadoExistente.isPresent()) {
            empleadoService.deleteEmpleado(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 200 OK o 404 NOT FOUND (Agregué validación de nulidad por precaución)
    @GetMapping("/usuarios")
    public ResponseEntity<EmpleadoUsuarioDTO> getEmpleadoConUsuario() {
        EmpleadoUsuarioDTO dto = empleadoService.getEmpleadoConUsuario();

        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 200 OK o 404 NOT FOUND (Agregué validación de nulidad por precaución)
    @GetMapping("/especialidades")
    public ResponseEntity<EmpleadoEspecialidadDTO> getEmpleadoConEspecialidad() {
        EmpleadoEspecialidadDTO dto = empleadoService.getEmpleadoConEspecialidad();

        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

