package com.peluqueria.ms_peluqueros_staff;

import com.peluqueria.ms_peluqueros_staff.model.Empleado;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionEmpleadoPrueba {

    @Test
    void registrarEmpleado(){
        Faker faker = new Faker();
        Empleado empleado = new Empleado();

        empleado.setPnombre(faker.name().firstName());
        empleado.setPnombre("Carlos");

        System.out.println(empleado);

        assertNotNull(empleado);
        assertNotNull(empleado.getPnombre());
        assertEquals("Carlos", empleado.getPnombre());
    }

    @Test
    void actualizarEmpleado(){
        Empleado empleado = new Empleado();
        empleado.setPnombre("Carlos");

        empleado.setPnombre("Andrés");

        assertNotNull(empleado);
        assertEquals("Andrés", empleado.getPnombre());
    }
}
