package com.peluqueria.ms_resenas_feedback;

import com.peluqueria.ms_resenas_feedback.model.Resena;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MsResenasFeedbackPrueba {

    @Test
    void registrarResena(){

        // Arrange (Preparar)
        Faker faker = new Faker();
        Resena resena = new Resena();

        resena.setEstrella(faker.number().numberBetween(1,6));
        resena.setComentario(faker.lorem().sentence());
        resena.setEstrella(5);

        // Act (Ejecutar)

        // Asseert (Verificar)
        System.out.println(resena);

        assertNotNull(resena);
        assertNotNull(resena.getComentario());
        assertEquals(5, resena.getEstrella());
    }
}
