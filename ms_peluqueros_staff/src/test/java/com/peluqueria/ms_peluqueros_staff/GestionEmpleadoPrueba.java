package com.peluqueria.ms_peluqueros_staff;

import com.peluqueria.ms_peluqueros_staff.model.Empleado;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionEmpleadoPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarEmpleado(){
        Empleado empleado = new Empleado();
        String nombreGenerado = faker.name().firstName();
        empleado.setPnombre(nombreGenerado);

        System.out.println(empleado);

        assertNotNull(empleado);
        assertNotNull(empleado.getPnombre());
        assertEquals(nombreGenerado, empleado.getPnombre());
    }

    @Test
    void actualizarEmpleado(){
        Empleado empleado = new Empleado();
        empleado.setPnombre(faker.name().firstName());

        String nuevoNombre = faker.name().firstName();
        empleado.setPnombre(nuevoNombre);

        assertNotNull(empleado);
        assertEquals(nuevoNombre, empleado.getPnombre());
    }
}
