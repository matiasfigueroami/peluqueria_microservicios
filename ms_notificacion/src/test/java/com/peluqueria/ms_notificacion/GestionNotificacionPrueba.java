package com.peluqueria.ms_notificacion;

import com.peluqueria.ms_notificacion.model.Notificacion;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionNotificacionPrueba {

    @Test
    void registrarNotificacion(){
        Faker faker = new Faker();
        Notificacion notificacion = new Notificacion();

        notificacion.setMensaje(faker.lorem().sentence());
        notificacion.setMensaje("Su cita fue confirmada");

        System.out.println(notificacion);

        assertNotNull(notificacion);
        assertNotNull(notificacion.getMensaje());
        assertEquals("Su cita fue confirmada", notificacion.getMensaje());
    }

    @Test
    void actualizarNotificacion(){
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("Su cita fue confirmada");

        notificacion.setMensaje("Su cita fue cancelada");

        assertNotNull(notificacion);
        assertEquals("Su cita fue cancelada", notificacion.getMensaje());
    }
}
