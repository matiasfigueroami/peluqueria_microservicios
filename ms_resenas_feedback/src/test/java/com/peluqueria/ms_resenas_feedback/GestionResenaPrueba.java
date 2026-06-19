package com.peluqueria.ms_resenas_feedback;

import com.peluqueria.ms_resenas_feedback.model.Resena;
import net.datafaker.Faker;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionResenaPrueba {

    @Test
    void registrarResena(){
        Faker faker = new Faker();
        Resena resena = new Resena();

        resena.setComentario(faker.lorem().sentence());
        resena.setComentario("Excelente atención");
        resena.setEstrella(5);

        System.out.println(resena);

        assertNotNull(resena);
        assertNotNull(resena.getComentario());
        assertEquals("Excelente atención", resena.getComentario());
        assertEquals(5, resena.getEstrella());
    }

    @Test
    void actualizarResena(){
        Resena resena = new Resena();
        resena.setEstrella(5);

        resena.setEstrella(3);

        assertNotNull(resena);
        assertEquals(3, resena.getEstrella());
    }

}
