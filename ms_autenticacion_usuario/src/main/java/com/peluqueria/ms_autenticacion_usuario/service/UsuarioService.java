package com.peluqueria.ms_autenticacion_usuario.service;

import com.peluqueria.ms_autenticacion_usuario.model.Usuario;
import com.peluqueria.ms_autenticacion_usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios(){return usuarioRepository.findAll();}

    public Optional<Usuario> getUsuario(Integer id){return  usuarioRepository.findById(id);}

    public Usuario saveUsuario(Usuario usuario){return usuarioRepository.save(usuario);}

    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
