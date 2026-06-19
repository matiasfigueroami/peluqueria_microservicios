package com.peluqueria.ms_servicios_precio;

import com.peluqueria.ms_servicios_precio.model.Servicio;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionServicioPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarServicio(){
        Servicio servicio = new Servicio();
        String nombreGenerado = faker.commerce().productName();
        int precioGenerado = faker.number().numberBetween(3000, 30000);

        servicio.setNombreServicio(nombreGenerado);
        servicio.setPrecio(precioGenerado);

        System.out.println(servicio);

        assertNotNull(servicio);
        assertNotNull(servicio.getNombreServicio());
        assertEquals(nombreGenerado, servicio.getNombreServicio());
        assertEquals(precioGenerado, servicio.getPrecio());
    }

    @Test
    void actualizarServicio(){
        Servicio servicio = new Servicio();
        servicio.setPrecio(faker.number().numberBetween(3000, 30000));

        int nuevoPrecio = faker.number().numberBetween(3000, 30000);
        servicio.setPrecio(nuevoPrecio);

        assertNotNull(servicio);
        assertEquals(nuevoPrecio, servicio.getPrecio());
    }
}
