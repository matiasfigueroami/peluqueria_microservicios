package com.peluqueria.ms_autenticacion_usuario;

import com.peluqueria.ms_autenticacion_usuario.model.Rol;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GestionRolPrueba {

    @Test
    void registrarRol() {

        Faker faker = new Faker();
        Rol rol = new Rol();

        rol.setNombreRol(faker.educator().course());
        rol.setNombreRol("Dueño");
        System.out.println(rol);

        assertNotNull(rol);
        assertNotNull(rol.getNombreRol());
        assertEquals("Dueño",rol.getNombreRol());


    }

    @Test
    void eliminarRol() {
        Rol rol = new Rol();
        rol.setNombreRol("Recepcionista");

        rol.setNombreRol(null);

        assertNull(rol.getNombreRol());
    }
}