package com.peluqueria.ms_servicios_precio;

import com.peluqueria.ms_servicios_precio.model.Servicio;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionServicioPrueba {

    @Test
    void registrarServicio(){
        Faker faker = new Faker();
        Servicio servicio = new Servicio();

        servicio.setNombreServicio(faker.commerce().productName());
        servicio.setNombreServicio("Corte de pelo");
        servicio.setPrecio(8000);

        System.out.println(servicio);

        assertNotNull(servicio);
        assertNotNull(servicio.getNombreServicio());
        assertEquals("Corte de pelo", servicio.getNombreServicio());
    }

    @Test
    void actualizarServicio(){
        Servicio servicio = new Servicio();
        servicio.setPrecio(8000);

        servicio.setPrecio(9500);

        assertNotNull(servicio);
        assertEquals(9500, servicio.getPrecio());
    }
}
