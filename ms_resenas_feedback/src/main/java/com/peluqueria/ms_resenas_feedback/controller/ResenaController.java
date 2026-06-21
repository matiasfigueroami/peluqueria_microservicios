package com.peluqueria.ms_resenas_feedback.controller;

import com.peluqueria.ms_resenas_feedback.dto.ResenaCitaDTO;
import com.peluqueria.ms_resenas_feedback.dto.ResenaEmpleadoDTO;
import com.peluqueria.ms_resenas_feedback.model.Resena;
import com.peluqueria.ms_resenas_feedback.service.ResenaService;
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
@RequestMapping("/api/v1/resenas")
@Tag(name = "Citas", description = "Operaciones relacionadas con las citas de MS Peluqueria")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Operation(summary = "Obtiene todos los detalles de las reseñas")
    @GetMapping
    public ResponseEntity<List<Resena>> getResenas() {
        return new ResponseEntity<>(resenaService.getResenas(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de una reseña mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Resena>> getResena(@PathVariable Integer id) {
        Optional<Resena> resenaOpt = resenaService.getResena(id);

        if (resenaOpt.isPresent()) {
            Resena resena = resenaOpt.get();
            EntityModel<Resena> model = EntityModel.of(resena);

            // Enlace a sí mismo (self)
            model.add(
                    linkTo(
                            methodOn(ResenaController.class).getResena(id)
                    ).withSelfRel()
            );

            // Enlace para eliminar (ajustado al endpoint de reseñas)
            model.add(
                    Link.of("http://localhost:8083/api/v1/resenas/" + id, "eliminar")
            );

            // Enlace hacia la lista de todas las reseñas
            model.add(
                    linkTo(
                            methodOn(ResenaController.class).getResenas()
                    ).withRel("todas-las-resenas")
            );

            // Retornamos un 200 OK pasando el modelo con los enlaces hipermedia
            return ResponseEntity.ok(model);

        } else {
            // Retornamos un 404 Not Found con el cuerpo vacío si no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Agrega una nueva reseña")
    @PostMapping
    public ResponseEntity<Resena> saveResena(@Valid @RequestBody Resena resena) {
        return new ResponseEntity<>(resenaService.saveResena(resena), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica una reseña existente mediante su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Resena> updateResena(@PathVariable Integer id,
                                               @Valid @RequestBody Resena resena) {
        Optional<Resena> resenaExistente = resenaService.getResena(id);
        if (resenaExistente.isPresent()) {
            resena.setIdResena(id);
            return new ResponseEntity<>(resenaService.updateResena(resena), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Elimina una reseña existente mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Integer id) {
        Optional<Resena> resenaExistente = resenaService.getResena(id);
        if (resenaExistente.isPresent()) {
            resenaService.deleteResena(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtiene todos los detalles de una reseña con el empleado asignado")
    @GetMapping("/empleados")
    public ResponseEntity<ResenaEmpleadoDTO> getResenaDeEmpleado() {
        return new ResponseEntity<>(resenaService.getResenaDeEmpleado(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de una reseña con la cita asignada")
    @GetMapping("/citas")
    public ResponseEntity<ResenaCitaDTO> getResenaDeCita() {
        return new ResponseEntity<>(resenaService.getResenaDeCita(), HttpStatus.OK);
    }
}
