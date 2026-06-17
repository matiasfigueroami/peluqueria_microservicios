package com.peluqueria.ms_clientes_perfil.controller;

import com.peluqueria.ms_clientes_perfil.dto.ClienteUsuarioDTO;
import com.peluqueria.ms_clientes_perfil.model.Cliente;
import com.peluqueria.ms_clientes_perfil.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getCliente() {
        return new ResponseEntity<>(clienteService.getClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Integer id) {
        Optional<Cliente> clienteOpt = clienteService.getCliente(id);
        if (clienteOpt.isPresent()) {
            return new ResponseEntity<>(clienteOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
    }

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

    @GetMapping("/usuarios")
    public ResponseEntity<ClienteUsuarioDTO> getClienteConUsuario() {
        return new ResponseEntity<>(clienteService.getClienteConUsuario(), HttpStatus.OK);
    }
}
