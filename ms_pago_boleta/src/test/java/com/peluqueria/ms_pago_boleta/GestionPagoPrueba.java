package com.peluqueria.ms_pago_boleta;

import com.peluqueria.ms_pago_boleta.model.Pago;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionPagoPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarPago(){
        Pago pago = new Pago();
        String metodoGenerado = faker.options().option("Efectivo", "Tarjeta", "Transferencia");
        int montoGenerado = faker.number().numberBetween(1000, 50000);

        pago.setMetodoPago(metodoGenerado);
        pago.setMontoTotal(montoGenerado);

        System.out.println(pago);

        assertNotNull(pago);
        assertNotNull(pago.getMetodoPago());
        assertEquals(metodoGenerado, pago.getMetodoPago());
        assertEquals(montoGenerado, pago.getMontoTotal());
    }

    @Test
    void actualizarPago(){
        Pago pago = new Pago();
        pago.setMetodoPago(faker.options().option("Efectivo", "Tarjeta"));

        String nuevoMetodo = faker.options().option("Transferencia", "Tarjeta");
        pago.setMetodoPago(nuevoMetodo);

        assertNotNull(pago);
        assertEquals(nuevoMetodo, pago.getMetodoPago());
    }
}
