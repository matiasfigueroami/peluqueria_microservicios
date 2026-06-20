package com.peluqueria.ms_agenda_cita.controller;

import com.peluqueria.ms_agenda_cita.dto.CitaClienteDTO;
import com.peluqueria.ms_agenda_cita.dto.CitaEmpleadoDTO;
import com.peluqueria.ms_agenda_cita.dto.CitaServicioDTO;
import com.peluqueria.ms_agenda_cita.model.Cita;
import com.peluqueria.ms_agenda_cita.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/citas")
@Tag(name = "Citas", description = "Operaciones relacionadas con las citas de MS Peluqueria")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Operation(summary = "Obtiene todos los detalles de las citas")
    @GetMapping
    public ResponseEntity<List<Cita>> getCitas() {
        return new ResponseEntity<>(citaService.getCitas(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de una cita mediante su ID")
    @GetMapping("/{id}")
    public EntityModel<Cita> getCita(@PathVariable Integer id){
        Cita cita = citaService.getCita(id).orElseThrow();
        EntityModel<Cita> model = EntityModel.of(cita);

        model.add(
                linkTo(
                       methodOn(CitaController.class).getCita(id)
                ).withSelfRel()
        );

        model.add(
                Link.of("http//localhost:8083/api/v1/citas/" + id, "eliminar")
        );

        model.add(
            linkTo(
                methodOn(CitaController.class)
                        .getCitas()
        ).withRel("todas-las-citas")
        );
        return model;
    }



    @Operation(summary = "Agrega una nueva cita")
    @PostMapping
    public ResponseEntity<?> saveCita(@Valid @RequestBody Cita cita) {
        Cita saved = citaService.saveCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Edita algunos datos de cita existente mediante su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable Integer id, @Valid @RequestBody Cita cita) {
        Optional<Cita> citaExistente = citaService.getCita(id);
        if (citaExistente.isPresent()) {
            cita.setIdCita(id);
            return new ResponseEntity<>(citaService.updateCita(cita), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Elimina una cita existente mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Integer id) {
        Optional<Cita> citaExistente = citaService.getCita(id);
        if (citaExistente.isPresent()) {
            citaService.deleteCita(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtiene los detalles de una cita y el cliente que lo agendo")
    @GetMapping("/clientes")
    public ResponseEntity<CitaClienteDTO> getCitaConClienteDTO() {
        return new ResponseEntity<>(citaService.getCitaConCliente(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los detalles de una cita y el empleado seleccionado")
    @GetMapping("/empleados")
    public ResponseEntity<CitaEmpleadoDTO> getCitaConEmpleadoDTO() {
        return new ResponseEntity<>(citaService.getCitaConEmpleado(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los detalles de una cita y el servicio asignado")
    @GetMapping("/servicios")
    public ResponseEntity<CitaServicioDTO> getCitaConServicioDTO() {
        return new ResponseEntity<>(citaService.getCitaConServicio(), HttpStatus.OK);
    }
}
