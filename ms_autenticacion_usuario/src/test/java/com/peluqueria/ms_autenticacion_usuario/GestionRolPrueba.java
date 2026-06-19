package com.peluqueria.ms_autenticacion_usuario;

import com.peluqueria.ms_autenticacion_usuario.model.Rol;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GestionRolPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarRol() {
        Rol rol = new Rol();
        String nombreGenerado = faker.options().option("Dueño", "Administrador", "Recepcionista");
        rol.setNombreRol(nombreGenerado);

        System.out.println(rol);

        assertNotNull(rol);
        assertNotNull(rol.getNombreRol());
        assertEquals(nombreGenerado, rol.getNombreRol());
    }

    @Test
    void eliminarRol() {
        Rol rol = new Rol();
        rol.setNombreRol(faker.job().title());

        rol.setNombreRol(null);

        assertNull(rol.getNombreRol());
    }
}
