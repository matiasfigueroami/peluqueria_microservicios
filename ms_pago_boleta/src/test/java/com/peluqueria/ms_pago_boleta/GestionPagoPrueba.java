package com.peluqueria.ms_pago_boleta;

import com.peluqueria.ms_pago_boleta.model.Pago;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionPagoPrueba {

    @Test
    void registrarPago(){
        Faker faker = new Faker();
        Pago pago = new Pago();

        pago.setMetodoPago(faker.business().creditCardType());
        pago.setMetodoPago("Efectivo");
        pago.setMontoTotal(15000);

        System.out.println(pago);

        assertNotNull(pago);
        assertNotNull(pago.getMetodoPago());
        assertEquals("Efectivo", pago.getMetodoPago());
    }

    @Test
    void actualizarPago(){
        Pago pago = new Pago();
        pago.setMetodoPago("Efectivo");

        pago.setMetodoPago("Tarjeta");

        assertNotNull(pago);
        assertEquals("Tarjeta", pago.getMetodoPago());
    }
}
