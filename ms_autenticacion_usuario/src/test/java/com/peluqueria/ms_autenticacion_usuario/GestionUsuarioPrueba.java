package com.peluqueria.ms_autenticacion_usuario;

import com.peluqueria.ms_autenticacion_usuario.model.Usuario;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GestionUsuarioPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarUsuario() {
        Usuario usuario = new Usuario();
        String emailGenerado = faker.internet().emailAddress();
        String passwordGenerado;
        passwordGenerado = faker.internet().password(8, 16, true, true, true);

        usuario.setEmail(emailGenerado);
        usuario.setPassword(passwordGenerado);
        usuario.setActivo(true);

        System.out.println(usuario);

        assertNotNull(usuario);
        assertEquals(emailGenerado, usuario.getEmail());
        assertEquals(passwordGenerado, usuario.getPassword());
        assertTrue(usuario.isActivo());
    }

    @Test
    void actualizarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setActivo(true);

        usuario.setActivo(false);

        assertNotNull(usuario);
        assertFalse(usuario.isActivo());
    }
}
