package com.peluqueria.ms_notificacion;

import com.peluqueria.ms_notificacion.model.Notificacion;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionNotificacionPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarNotificacion(){
        Notificacion notificacion = new Notificacion();
        String mensajeGenerado = faker.lorem().sentence();
        notificacion.setMensaje(mensajeGenerado);

        System.out.println(notificacion);

        assertNotNull(notificacion);
        assertNotNull(notificacion.getMensaje());
        assertEquals(mensajeGenerado, notificacion.getMensaje());
    }

    @Test
    void actualizarNotificacion(){
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(faker.lorem().sentence());

        String nuevoMensaje = faker.lorem().sentence();
        notificacion.setMensaje(nuevoMensaje);

        assertNotNull(notificacion);
        assertEquals(nuevoMensaje, notificacion.getMensaje());
    }
}
