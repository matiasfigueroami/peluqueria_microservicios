package com.peluqueria.ms_resenas_feedback;

import com.peluqueria.ms_resenas_feedback.model.Resena;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionResenaPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarResena(){
        Resena resena = new Resena();
        String comentarioGenerado = faker.lorem().sentence();
        int estrellaGenerada = faker.number().numberBetween(1, 5);

        resena.setComentario(comentarioGenerado);
        resena.setEstrella(estrellaGenerada);

        System.out.println(resena);

        assertNotNull(resena);
        assertNotNull(resena.getComentario());
        assertEquals(comentarioGenerado, resena.getComentario());
        assertEquals(estrellaGenerada, resena.getEstrella());
    }

    @Test
    void actualizarResena(){
        Resena resena = new Resena();
        resena.setEstrella(faker.number().numberBetween(1, 5));

        int nuevaEstrella = faker.number().numberBetween(1, 5);
        resena.setEstrella(nuevaEstrella);

        assertNotNull(resena);
        assertEquals(nuevaEstrella, resena.getEstrella());
    }

}
