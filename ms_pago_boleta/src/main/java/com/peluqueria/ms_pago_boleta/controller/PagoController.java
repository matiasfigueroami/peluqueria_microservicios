package com.peluqueria.ms_pago_boleta.controller;

import com.peluqueria.ms_pago_boleta.dto.PagoCitaDTO;
import com.peluqueria.ms_pago_boleta.model.Pago;
import com.peluqueria.ms_pago_boleta.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<Pago>> getPagos() {
        return new ResponseEntity<>(pagoService.getPagos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPago(@PathVariable Integer id) {
        Optional<Pago> pagoOpt = pagoService.getPago(id);
        if (pagoOpt.isPresent()) {
            return new ResponseEntity<>(pagoOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pago> savePago(@Valid @RequestBody Pago pago) {
        return new ResponseEntity<>(pagoService.savePago(pago), HttpStatus.CREATED);
    }

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

    @GetMapping("/citas")
    public ResponseEntity<PagoCitaDTO> getPagoConCita() {
        return new ResponseEntity<>(pagoService.getPagoConCita(), HttpStatus.OK);
    }
}
