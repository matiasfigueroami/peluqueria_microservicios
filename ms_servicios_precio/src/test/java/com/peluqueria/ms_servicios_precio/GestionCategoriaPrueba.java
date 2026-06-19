package com.peluqueria.ms_servicios_precio;

import com.peluqueria.ms_servicios_precio.model.Categoria;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GestionCategoriaPrueba {

    @Test
    void registrarCategoria(){
        Faker faker = new Faker();
        Categoria categoria = new Categoria();

        categoria.setNombreCategoria(faker.commerce().department());
        categoria.setNombreCategoria("Cabello");

        System.out.println(categoria);

        assertNotNull(categoria);
        assertNotNull(categoria.getNombreCategoria());
        assertEquals("Cabello", categoria.getNombreCategoria());
    }

    @Test
    void actualizarCategoria(){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria("Cabello");

        categoria.setNombreCategoria("Uñas");

        assertNotNull(categoria);
        assertEquals("Uñas", categoria.getNombreCategoria());
    }
}
