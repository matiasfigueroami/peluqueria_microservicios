package com.peluqueria.ms_servicios_precio.service;

import com.peluqueria.ms_servicios_precio.model.Categoria;
import com.peluqueria.ms_servicios_precio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria saveCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> getCategoria(Integer id){
        return categoriaRepository.findById(id);
    }

    public Categoria updateCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Integer id){
        categoriaRepository.deleteById(id);
    }
}
