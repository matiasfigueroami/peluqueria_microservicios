package com.peluqueria.ms_agenda_cita;

import com.peluqueria.ms_agenda_cita.model.Cita;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionAgendaCitaPrueba {
    @Test
    void registrarAgendaCita(){
        //arange
        Faker faker= new Faker();
        Cita cita = new Cita();
        cita.setEstado(faker.educator().course());
        cita.setEstado("activo");

        System.out.println(cita);

        assertNotNull(cita);

        assertNotNull(cita);
        assertNotNull(cita.getEstado());
        assertEquals("activo",cita.getEstado());


    }

    @Test
    void actualizarAgendaCita(){
        Cita cita = new Cita();
        cita.setEstado("pendiente");

        cita.setEstado("cancelado");

        System.out.println(cita);

        assertNotNull(cita);
        assertEquals("cancelado", cita.getEstado());
    }

}
