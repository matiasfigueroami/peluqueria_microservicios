package com.peluqueria.ms_especialidades_catalogo.controller;

import com.peluqueria.ms_especialidades_catalogo.model.Especialidad;
import com.peluqueria.ms_especialidades_catalogo.service.EspecialidadService;
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
@RequestMapping("/api/v1/especialidades")
@Tag(name = "Especialidades", description = "Operaciones relacionadas con las especialidades de MS Peluqueria")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @Operation(summary = "Obtiene todos los detalles de las especialidades")
    @GetMapping
    public ResponseEntity<List<Especialidad>> getEspecialidades() {
        return new ResponseEntity<>(especialidadService.getEspecialidades(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de una especialidad")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Especialidad>> getEspecialidad(@PathVariable Integer id) {
        Optional<Especialidad> especialidadOpt = especialidadService.getEspecialidad(id);

        if (especialidadOpt.isPresent()) {
            Especialidad especialidad = especialidadOpt.get();
            EntityModel<Especialidad> model = EntityModel.of(especialidad);

            // Enlace a sí mismo (self)
            model.add(
                    linkTo(
                            methodOn(EspecialidadController.class).getEspecialidad(id)
                    ).withSelfRel()
            );
            model.add(
                    Link.of("http://localhost:8083/api/v1/especialidades/" + id, "eliminar")
            );

            model.add(
                    linkTo(
                            methodOn(EspecialidadController.class).getEspecialidades()
                    ).withRel("todas-las-especialidades")
            );

            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Agrega una nueva especialidad")
    @PostMapping
    public ResponseEntity<Especialidad> saveEspecialidad(@Valid @RequestBody Especialidad especialidad) {
        return new ResponseEntity<>(especialidadService.saveEspecialidad(especialidad), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica una especialidad existente mediante su ID")
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

    @Operation(summary = "Elimina una especialidad existente mediante su ID")
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
