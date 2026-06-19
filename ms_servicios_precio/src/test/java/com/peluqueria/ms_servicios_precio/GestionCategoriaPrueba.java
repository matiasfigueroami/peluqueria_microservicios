package com.peluqueria.ms_servicios_precio;

import com.peluqueria.ms_servicios_precio.model.Categoria;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionCategoriaPrueba {

    private final Faker faker = new Faker();

    @Test
    void registrarCategoria(){
        Categoria categoria = new Categoria();
        String nombreGenerado = faker.commerce().department();
        categoria.setNombreCategoria(nombreGenerado);

        System.out.println(categoria);

        assertNotNull(categoria);
        assertNotNull(categoria.getNombreCategoria());
        assertEquals(nombreGenerado, categoria.getNombreCategoria());
    }

    @Test
    void actualizarCategoria(){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(faker.commerce().department());

        String nuevoNombre = faker.commerce().department();
        categoria.setNombreCategoria(nuevoNombre);

        assertNotNull(categoria);
        assertEquals(nuevoNombre, categoria.getNombreCategoria());
    }
}
