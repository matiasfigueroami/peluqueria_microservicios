package com.peluqueria.ms_bloqueos_horario.controller;

import com.peluqueria.ms_bloqueos_horario.dto.BloqueoHorarioEmpleadoDTO;
import com.peluqueria.ms_bloqueos_horario.model.BloqueoHorario;
import com.peluqueria.ms_bloqueos_horario.service.BloqueoHorarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
@RestController
@RequestMapping("/api/v1/bloqueoHorarios")
@Tag(name = "Bloqueo Horarios", description = "Operaciones relacionadas con los bloqueos de horarios de MS Peluqueria")
public class BloqueoHorarioController {

    @Autowired
    private BloqueoHorarioService bloqueoHorarioService;

    @Operation(summary = "Obtiene todos los detalles de los horarios")
    @GetMapping
    public ResponseEntity<List<BloqueoHorario>> getBloqueoHorarios() {
        return new ResponseEntity<>(bloqueoHorarioService.getBloqueoHorario(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de un horario mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BloqueoHorario>> getBloqueoHorario(@PathVariable Integer id) {
        Optional<BloqueoHorario> bloqueoOpt = bloqueoHorarioService.getBloqueoHorario(id);

        if (bloqueoOpt.isPresent()) {
            BloqueoHorario bloqueo = bloqueoOpt.get();
            EntityModel<BloqueoHorario> model = EntityModel.of(bloqueo);

            model.add(
                    linkTo(
                            methodOn(BloqueoHorarioController.class).getBloqueoHorario(id)
                    ).withSelfRel()
            );

            model.add(
                    Link.of("http://localhost:8083/api/v1/bloqueos-horario/" + id, "eliminar")
            );

            model.add(
                    linkTo(
                            methodOn(BloqueoHorarioController.class).getBloqueoHorarios()
                    ).withRel("todos-los-horarios")
            );

            // Retornamos el modelo con HATEOAS y el estado HTTP 200 OK
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            // Si no existe, retornamos un HTTP 404 Not Found con el cuerpo vacío
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Agrega un nuevo bloqueo horario")
    @PostMapping
    public ResponseEntity<?> saveBloqueoHorario(@RequestBody BloqueoHorario bloqueoHorario) {
        try {
            BloqueoHorario saved = bloqueoHorarioService.saveBloqueoHorario(bloqueoHorario);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Modificas un bloqueo horario existente mediante su ID")
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

    @Operation(summary = "Elimina un bloqueo horario existente mediante su ID")
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

    @Operation(summary = "Obtiene todos los detalles un bloqueo horario con el empleado asignado")
    @GetMapping("/empleados")
    public ResponseEntity<BloqueoHorarioEmpleadoDTO> getBloqueoHorarioConEmpleado() {
        return new ResponseEntity<>(bloqueoHorarioService.getBloqueoHorarioConEmpleado(), HttpStatus.OK);
    }
}
