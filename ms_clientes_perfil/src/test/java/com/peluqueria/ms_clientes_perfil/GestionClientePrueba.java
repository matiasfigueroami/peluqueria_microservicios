package com.peluqueria.ms_clientes_perfil;

import com.peluqueria.ms_clientes_perfil.model.Cliente;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionClientePrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarCliente(){
        Cliente cliente = new Cliente();
        String nombreGenerado = faker.name().firstName();
        String telefonoGenerado = faker.phoneNumber().subscriberNumber(9);

        cliente.setPrimerNombre(nombreGenerado);
        cliente.setTelefono(telefonoGenerado);

        System.out.println(cliente);

        assertNotNull(cliente);
        assertNotNull(cliente.getPrimerNombre());
        assertEquals(nombreGenerado, cliente.getPrimerNombre());
        assertEquals(telefonoGenerado, cliente.getTelefono());
    }

    @Test
    void actualizarCliente(){
        Cliente cliente = new Cliente();
        cliente.setTelefono(faker.phoneNumber().subscriberNumber(9));

        String nuevoTelefono = faker.phoneNumber().subscriberNumber(9);
        cliente.setTelefono(nuevoTelefono);

        assertNotNull(cliente);
        assertEquals(nuevoTelefono, cliente.getTelefono());
    }
}
