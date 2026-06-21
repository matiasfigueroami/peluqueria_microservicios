package com.peluqueria.ms_servicios_precio.controller;

import com.peluqueria.ms_servicios_precio.model.Categoria;
import com.peluqueria.ms_servicios_precio.service.CategoriaService;
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
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorias de MS Peluqueria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Obtiene todos los detalles de las categorias")
    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias() {
        return new ResponseEntity<>(categoriaService.getCategorias(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de una categoria mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> getCategoria(@PathVariable Integer id) {
        Optional<Categoria> categoriaOpt = categoriaService.getCategoria(id);

        if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
            EntityModel<Categoria> model = EntityModel.of(categoria);

            // Enlace a sí mismo (self)
            model.add(
                    linkTo(
                            methodOn(CategoriaController.class).getCategoria(id)
                    ).withSelfRel()
            );

            // Enlace para eliminar (ajustado al endpoint de categorias)
            model.add(
                    Link.of("http://localhost:8083/api/v1/categorias/" + id, "eliminar")
            );

            // Enlace hacia la lista de todas las categorias
            model.add(
                    linkTo(
                            methodOn(CategoriaController.class).getCategorias()
                    ).withRel("todas-las-categorias")
            );

            // Retornamos un 200 OK pasando el modelo con los enlaces hipermedia
            return ResponseEntity.ok(model);

        } else {
            // Retornamos un 404 Not Found con el cuerpo vacío si no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary = "Agrega una nueva categoria")
    @PostMapping
    public ResponseEntity<Categoria> saveCategoria(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.saveCategoria(categoria), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica una categoria existente mediante su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id,
                                                     @Valid @RequestBody Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaService.getCategoria(id);
        if (categoriaExistente.isPresent()) {
            categoria.setIdCategoria(id);
            return new ResponseEntity<>(categoriaService.updateCategoria(categoria), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar una categoria existente mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        Optional<Categoria> categoriaExistente = categoriaService.getCategoria(id);
        if (categoriaExistente.isPresent()) {
            categoriaService.deleteCategoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
