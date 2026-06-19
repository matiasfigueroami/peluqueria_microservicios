package com.peluqueria.ms_especialidades_catalogo;

import com.peluqueria.ms_especialidades_catalogo.model.Especialidad;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionEspecialidadPrueba {

    @Test
    void registrarEspecialidad(){
        Faker faker = new Faker();
        Especialidad especialidad = new Especialidad();

        especialidad.setNombre(faker.job().field());
        especialidad.setNombre("Coloración");

        System.out.println(especialidad);

        assertNotNull(especialidad);
        assertNotNull(especialidad.getNombre());
        assertEquals("Coloración", especialidad.getNombre());
    }

    @Test
    void actualizarEspecialidad(){
        Especialidad especialidad = new Especialidad();
        especialidad.setNombre("Coloración");

        especialidad.setNombre("Corte");

        assertNotNull(especialidad);
        assertEquals("Corte", especialidad.getNombre());
    }
}
