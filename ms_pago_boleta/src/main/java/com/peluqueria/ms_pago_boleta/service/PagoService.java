package com.peluqueria.ms_pago_boleta.service;

import com.peluqueria.ms_pago_boleta.client.CitaClient;
import com.peluqueria.ms_pago_boleta.dto.CitaDTO;
import com.peluqueria.ms_pago_boleta.dto.PagoCitaDTO;
import com.peluqueria.ms_pago_boleta.model.Pago;
import com.peluqueria.ms_pago_boleta.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> getPagos(){
        return pagoRepository.findAll();
    }

    public Pago savePago(Pago pago){
        return pagoRepository.save(pago);
    }

    public Optional<Pago>getPago(Integer id){
        return pagoRepository.findById(id);
    }

    public Pago updatePago(Pago pago){
        return pagoRepository.save(pago);
    }

    public void deletePago(Integer id){
        pagoRepository.deleteById(id);
    }

    @Autowired
    private CitaClient citaClient;

    public PagoCitaDTO getPagoConCita(){

        Integer idPago = 1;
        Integer idCita = 1;

        Pago pago =
                pagoRepository.findById(idPago).orElse(null);

        CitaDTO cita =
                citaClient.obtenerCita(idCita);

        PagoCitaDTO dto =
                new PagoCitaDTO();

        dto.setIdPago(pago.getIdPago());
        dto.setMontoTotal(pago.getMontoTotal());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setFechaPago(pago.getFechaPago());

        dto.setCita(cita);

        return dto;
    }
}
