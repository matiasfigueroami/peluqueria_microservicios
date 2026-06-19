package com.peluqueria.ms_bloqueos_horario;

import com.peluqueria.ms_bloqueos_horario.model.BloqueoHorario;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionBloqueoHorarioPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarBloqueoHorario(){
        BloqueoHorario bloqueo = new BloqueoHorario();
        String motivoGenerado = faker.lorem().sentence();
        bloqueo.setMotivo(motivoGenerado);

        System.out.println(bloqueo);

        assertNotNull(bloqueo);
        assertNotNull(bloqueo.getMotivo());
        assertEquals(motivoGenerado, bloqueo.getMotivo());
    }

    @Test
    void actualizarBloqueoHorario(){
        BloqueoHorario bloqueo = new BloqueoHorario();
        bloqueo.setMotivo(faker.lorem().sentence());

        String nuevoMotivo = faker.lorem().sentence();
        bloqueo.setMotivo(nuevoMotivo);

        assertNotNull(bloqueo);
        assertEquals(nuevoMotivo, bloqueo.getMotivo());
    }
}
