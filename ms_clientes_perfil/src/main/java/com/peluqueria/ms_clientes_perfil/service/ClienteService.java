package com.peluqueria.ms_clientes_perfil.service;

import com.peluqueria.ms_clientes_perfil.client.UsuarioClient;
import com.peluqueria.ms_clientes_perfil.dto.ClienteUsuarioDTO;
import com.peluqueria.ms_clientes_perfil.dto.UsuarioDTO;
import com.peluqueria.ms_clientes_perfil.model.Cliente;
import com.peluqueria.ms_clientes_perfil.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

@Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes(){return clienteRepository.findAll();}

    public Optional<Cliente> getCliente(Integer id){return  clienteRepository.findById(id);}

    public Cliente saveCliente(Cliente cliente){return clienteRepository.save(cliente);}

    public Cliente updateCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Autowired
    private UsuarioClient usuarioClient;

    public ClienteUsuarioDTO getClienteConUsuario() {

        Integer idCliente = 1;
        Integer idUsuario = 3;

        Cliente cliente =
                clienteRepository.findById(idCliente).orElse(null);

        UsuarioDTO usuario =
                usuarioClient.obtenerUsuario(idUsuario);

        ClienteUsuarioDTO dto =
                new ClienteUsuarioDTO();

        dto.setIdCliente(cliente.getIdCliente());
        dto.setPrimerNombre(cliente.getPrimerNombre());
        dto.setApellidoPaterno(cliente.getApellidoPaterno());
        dto.setTelefono(cliente.getTelefono());
        dto.setNotasAlergia(cliente.getNotasAlergia());

        dto.setUsuario(usuario);

        return dto;
    }

}
