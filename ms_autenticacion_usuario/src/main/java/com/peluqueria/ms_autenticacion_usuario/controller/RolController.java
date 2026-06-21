package com.peluqueria.ms_autenticacion_usuario.controller;

import com.peluqueria.ms_autenticacion_usuario.model.Rol;
import com.peluqueria.ms_autenticacion_usuario.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;


import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "Operaciones relacionadas con los roles de MS Peluqueria")
public class RolController {

    @Autowired
    private RolService rolService;

    @Operation(summary = "Obtiene todos los detalles de los roles")
    @GetMapping
    public ResponseEntity<List<Rol>> getRoles() {
        return new ResponseEntity<>(rolService.getRoles(), HttpStatus.OK);
    }


    @Operation(summary = "Obtiene todos los detalles de un rol mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Rol>> getRol(@PathVariable Integer id) {
        Optional<Rol> rolOpt = rolService.getRol(id);

        if (rolOpt.isPresent()) {
            Rol rol = rolOpt.get();
            EntityModel<Rol> model = EntityModel.of(rol);

            model.add(
                    linkTo(
                            methodOn(RolController.class).getRol(id)
                    ).withSelfRel()
            );

            model.add(
                    Link.of("http://localhost:8083/api/v1/roles/" + id, "eliminar")
            );

            model.add(
                    linkTo(
                            methodOn(RolController.class).getRoles()
                    ).withRel("todos-los-roles")
            );
            return ResponseEntity.ok(model);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Agrega un nuevo rol")
    @PostMapping
    public ResponseEntity<Rol> saveRol(@Valid @RequestBody Rol rolUsuario) {
        return new ResponseEntity<>(rolService.saveRol(rolUsuario), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un rol existente mediante su ID")
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

    @Operation(summary = "Elimina un rol existente mediante su ID")
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
