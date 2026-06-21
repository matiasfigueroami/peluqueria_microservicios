package com.peluqueria.ms_clientes_perfil.controller;

import com.peluqueria.ms_clientes_perfil.dto.ClienteUsuarioDTO;
import com.peluqueria.ms_clientes_perfil.model.Cliente;
import com.peluqueria.ms_clientes_perfil.service.ClienteService;
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
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes de MS Peluqueria")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtiene todos los detalles de los clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return new ResponseEntity<>(clienteService.getClientes(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de un cliente")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> getCliente(@PathVariable Integer id) {
        Optional<Cliente> clienteOpt = clienteService.getCliente(id);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            EntityModel<Cliente> model = EntityModel.of(cliente);

            // Enlace a sí mismo (self)
            model.add(
                    linkTo(
                            methodOn(ClienteController.class).getCliente(id)
                    ).withSelfRel()
            );

            // Enlace para eliminar (ajustado al endpoint de clientes)
            model.add(
                    Link.of("http://localhost:8083/api/v1/clientes/" + id, "eliminar")
            );

            // Enlace hacia la lista de todos los clientes
            model.add(
                    linkTo(
                            methodOn(ClienteController.class).getClientes()
                    ).withRel("todos-los-clientes")
            );

            // Retornamos el modelo con los enlaces y el estado 200 OK
            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {
            // Retornamos 404 si el cliente no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Agrega un nuevo cliente")
    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un cliente existente mediante su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteService.getCliente(id);
        if (clienteExistente.isPresent()) {
            cliente.setIdCliente(id);
            return new ResponseEntity<>(clienteService.updateCliente(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Elimina un cliente existente mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> clienteExistente = clienteService.getCliente(id);
        if (clienteExistente.isPresent()) {
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtiene todos los detalles de un cliente con su usuario")
    @GetMapping("/usuarios")
    public ResponseEntity<ClienteUsuarioDTO> getClienteConUsuario() {
        return new ResponseEntity<>(clienteService.getClienteConUsuario(), HttpStatus.OK);
    }
}
