package com.peluqueria.ms_especialidades_catalogo;

import com.peluqueria.ms_especialidades_catalogo.model.Especialidad;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionEspecialidadPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarEspecialidad(){
        Especialidad especialidad = new Especialidad();
        String nombreGenerado = faker.job().field();
        especialidad.setNombre(nombreGenerado);

        System.out.println(especialidad);

        assertNotNull(especialidad);
        assertNotNull(especialidad.getNombre());
        assertEquals(nombreGenerado, especialidad.getNombre());
    }

    @Test
    void actualizarEspecialidad(){
        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(faker.job().field());

        String nuevoNombre = faker.job().field();
        especialidad.setNombre(nuevoNombre);

        assertNotNull(especialidad);
        assertEquals(nuevoNombre, especialidad.getNombre());
    }
}
