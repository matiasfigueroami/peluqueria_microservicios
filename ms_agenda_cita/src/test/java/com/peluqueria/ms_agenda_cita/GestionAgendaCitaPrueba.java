package com.peluqueria.ms_agenda_cita;

import com.peluqueria.ms_agenda_cita.model.Cita;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionAgendaCitaPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarAgendaCita(){
        Cita cita = new Cita();
        String estadoGenerado = faker.options().option("pendiente", "activo", "completada");
        cita.setEstado(estadoGenerado);

        System.out.println(cita);

        assertNotNull(cita);
        assertNotNull(cita.getEstado());
        assertEquals(estadoGenerado, cita.getEstado());
    }

    @Test
    void actualizarAgendaCita(){
        Cita cita = new Cita();
        cita.setEstado(faker.options().option("pendiente", "activo"));

        String nuevoEstado = faker.options().option("cancelado", "completada");
        cita.setEstado(nuevoEstado);

        System.out.println(cita);

        assertNotNull(cita);
        assertEquals(nuevoEstado, cita.getEstado());
    }

}
