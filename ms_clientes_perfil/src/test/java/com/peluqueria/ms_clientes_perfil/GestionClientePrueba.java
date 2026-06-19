package com.peluqueria.ms_clientes_perfil;

import com.peluqueria.ms_clientes_perfil.model.Cliente;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionClientePrueba {

    @Test
    void registrarCliente(){
        Faker faker = new Faker();
        Cliente cliente = new Cliente();

        cliente.setPrimerNombre(faker.name().firstName());
        cliente.setPrimerNombre("Juan");
        cliente.setTelefono("987654321");

        System.out.println(cliente);

        assertNotNull(cliente);
        assertNotNull(cliente.getPrimerNombre());
        assertEquals("Juan", cliente.getPrimerNombre());
    }

    @Test
    void actualizarCliente(){
        Cliente cliente = new Cliente();
        cliente.setTelefono("987654321");

        cliente.setTelefono("912345678");

        assertNotNull(cliente);
        assertEquals("912345678", cliente.getTelefono());
    }
}
