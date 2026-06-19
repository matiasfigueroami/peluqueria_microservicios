package com.peluqueria.ms_autenticacion_usuario;

import com.peluqueria.ms_autenticacion_usuario.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GestionUsuarioPrueba {

    @Test
    void registrarUsuario() {
        // 1. Arrange (Preparar los datos manuales)
        Usuario usuario = new Usuario();
        String correoEsperado = "juan@correo.com";
        String claveEsperada = "admin123";

        // 2. Act (Asignar los datos al usuario)
        usuario.setEmail(correoEsperado);
        usuario.setPassword(claveEsperada);
        usuario.setActivo(true);

        // Imprimimos para ver el resultado en consola
        System.out.println(usuario);

        // 3. Assert (Verificar que los datos se guardaron bien)
        assertNotNull(usuario);

        // Comprobamos que el email y la contraseña sean exactamente los que guardamos
        assertEquals(correoEsperado, usuario.getEmail());
        assertEquals(claveEsperada, usuario.getPassword());

        // Comprobamos que el usuario esté activo
        assertTrue(usuario.isActivo());
    }

    @Test
    void actualizarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("juan@correo.com");
        usuario.setActivo(true);

        usuario.setActivo(false);

        assertNotNull(usuario);
        assertEquals(false, usuario.isActivo());
    }
}