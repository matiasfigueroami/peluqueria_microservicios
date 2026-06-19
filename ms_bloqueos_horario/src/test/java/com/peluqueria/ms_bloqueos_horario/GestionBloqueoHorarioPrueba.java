package com.peluqueria.ms_bloqueos_horario;

import com.peluqueria.ms_bloqueos_horario.model.BloqueoHorario;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionBloqueoHorarioPrueba {

    @Test
    void registrarBloqueoHorario(){
        Faker faker = new Faker();
        BloqueoHorario bloqueo = new BloqueoHorario();

        bloqueo.setMotivo(faker.lorem().sentence());
        bloqueo.setMotivo("Vacaciones");

        System.out.println(bloqueo);

        assertNotNull(bloqueo);
        assertNotNull(bloqueo.getMotivo());
        assertEquals("Vacaciones", bloqueo.getMotivo());
    }

    @Test
    void actualizarBloqueoHorario(){
        BloqueoHorario bloqueo = new BloqueoHorario();
        bloqueo.setMotivo("Vacaciones");

        bloqueo.setMotivo("Licencia médica");

        assertNotNull(bloqueo);
        assertEquals("Licencia médica", bloqueo.getMotivo());
    }
}
