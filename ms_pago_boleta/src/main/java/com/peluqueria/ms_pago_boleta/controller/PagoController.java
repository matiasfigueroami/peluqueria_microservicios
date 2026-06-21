package com.peluqueria.ms_pago_boleta.controller;

import com.peluqueria.ms_pago_boleta.dto.PagoCitaDTO;
import com.peluqueria.ms_pago_boleta.model.Pago;
import com.peluqueria.ms_pago_boleta.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con los pagos de MS Peluqueria")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    @Operation(summary = "Obtiene todos los detalles de los pagos")
    public ResponseEntity<List<Pago>> getPagos() {
        return new ResponseEntity<>(pagoService.getPagos(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los detalles de un pago mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pago>> getPago(@PathVariable Integer id) {
        Optional<Pago> pagoOpt = pagoService.getPago(id);

        if (pagoOpt.isPresent()) {
            Pago pago = pagoOpt.get();
            EntityModel<Pago> model = EntityModel.of(pago);

            model.add(
                    linkTo(
                            methodOn(PagoController.class).getPago(id)
                    ).withSelfRel()
            );


            model.add(
                    Link.of("http://localhost:8083/api/v1/pagos/" + id, "eliminar")
            );


            model.add(
                    linkTo(
                            methodOn(PagoController.class).getPagos()
                    ).withRel("todos-los-pagos")
            );


            return new ResponseEntity<>(model, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Agrega un nuevo pago")
    @PostMapping
    public ResponseEntity<Pago> savePago(@Valid @RequestBody Pago pago) {
        return new ResponseEntity<>(pagoService.savePago(pago), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un pago existente mediante su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Pago> updatePago(@PathVariable Integer id,
                                           @Valid @RequestBody Pago pago) {
        Optional<Pago> pagoExistente = pagoService.getPago(id);
        if (pagoExistente.isPresent()) {
            pago.setIdPago(id);
            return new ResponseEntity<>(pagoService.updatePago(pago), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Elimina un pago existente mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Integer id) {
        Optional<Pago> pagoExistente = pagoService.getPago(id);
        if (pagoExistente.isPresent()) {
            pagoService.deletePago(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtiene todos los detalles de un pago con la cita pedida")
    @GetMapping("/citas")
    public ResponseEntity<PagoCitaDTO> getPagoConCita() {
        return new ResponseEntity<>(pagoService.getPagoConCita(), HttpStatus.OK);
    }
}
