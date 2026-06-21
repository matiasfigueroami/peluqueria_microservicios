package com.peluqueria.ms_autenticacion_usuario.controller;

import com.peluqueria.ms_autenticacion_usuario.model.Usuario;
import com.peluqueria.ms_autenticacion_usuario.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios de MS Peluqueria")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtiene todos los detalles de los usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de un usuario")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> getUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.getUsuario(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            EntityModel<Usuario> model = EntityModel.of(usuario);

            // Enlace a sí mismo (self)
            model.add(
                    linkTo(
                            methodOn(UsuarioController.class).getUsuario(id)
                    ).withSelfRel()
            );

            // Enlace para eliminar (ajustado al endpoint de usuarios)
            model.add(
                    Link.of("http://localhost:8083/api/v1/usuarios/" + id, "eliminar")
            );

            // Enlace hacia la lista de todos los usuarios
            model.add(
                    linkTo(
                            methodOn(UsuarioController.class).getUsuarios()
                    ).withRel("todos-los-usuarios")
            );

            // Retornamos el modelo con los enlaces y el estado 200 OK
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            // Retornamos 404 si el usuario no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Agrega un nuevo usuario")
    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un usuario existente mediante su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.getUsuario(id);
        if (usuarioExistente.isPresent()) {
            usuario.setIdUsuario(id);
            return new ResponseEntity<>(usuarioService.updateUsuario(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Elimina un usuario existente mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuarioExistente = usuarioService.getUsuario(id);
        if (usuarioExistente.isPresent()) {
            usuarioService.deleteUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
