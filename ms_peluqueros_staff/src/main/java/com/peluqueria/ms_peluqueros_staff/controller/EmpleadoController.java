package com.peluqueria.ms_peluqueros_staff.controller;

import com.peluqueria.ms_peluqueros_staff.dto.EmpleadoEspecialidadDTO;
import com.peluqueria.ms_peluqueros_staff.dto.EmpleadoUsuarioDTO;
import com.peluqueria.ms_peluqueros_staff.model.Empleado;
import com.peluqueria.ms_peluqueros_staff.service.EmpleadoService;
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
@RequestMapping("/api/v1/empleados")
@Tag(name = "Empleados", description = "Operaciones relacionadas con los empleados de MS Peluqueria")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // 200 OK
    @Operation(summary = "Obtiene todos los detalles de los empleados")
    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {
        return ResponseEntity.ok(empleadoService.getEmpleados());
    }

    // 200 OK o 404 NOT FOUND
    @Operation(summary = "Obtiene todos los detalles de un empleado mediante su ID")
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
    @Operation(summary = "Agrega un nuevo empleado")
    @PostMapping
    public ResponseEntity<Empleado> saveEmpleado(@Valid @RequestBody Empleado empleado) {
        Empleado empleadoGuardado = empleadoService.saveEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado);
    }

    // 200 OK o 404 NOT FOUND (Se agrega @Valid)
    @Operation(summary = "Modifica un empleado mediante su ID")
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
    @Operation(summary = "Elimina un empleado mediante su ID")
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
    @Operation(summary = "Obtiene todos los detalles de un empleado con su usuario")
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
    @Operation(summary = "Obtiene todos los detalles de un empleado con su especialidad")
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

