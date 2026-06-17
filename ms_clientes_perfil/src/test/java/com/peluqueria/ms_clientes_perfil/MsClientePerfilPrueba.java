package com.peluqueria.ms_clientes_perfil;

import com.peluqueria.ms_clientes_perfil.model.Cliente;
import com.peluqueria.ms_clientes_perfil.repository.ClienteRepository;
import com.peluqueria.ms_clientes_perfil.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MsClientePerfilPrueba {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteValido;

    @BeforeEach
    void setUp() {
        clienteValido = new Cliente();
        clienteValido.setIdCliente(1);
        clienteValido.setIdUsuario(10);
        clienteValido.setPrimerNombre("Matias");
        clienteValido.setApellidoPaterno("Figueroa");
        clienteValido.setTelefono("+56912345678");
        clienteValido.setNotasAlergia("Ninguna");
    }

    // ==========================================
    // CP-16: Crear perfil de cliente
    // ==========================================
    @Test
    void testCrearPerfilDeClienteExitoso() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteValido);

        Cliente resultado = clienteService.saveCliente(clienteValido);

        assertNotNull(resultado);
        assertEquals("Matias", resultado.getPrimerNombre());
        assertEquals("Figueroa", resultado.getApellidoPaterno());
        assertEquals("+56912345678", resultado.getTelefono());
        verify(clienteRepository, times(1)).save(clienteValido);
    }

    // ==========================================
    // CP-17: Actualizar datos del perfil
    // ==========================================
    @Test
    void testActualizarDatosDelPerfilSePersistenCorrectamente() {
        Cliente clienteModificado = new Cliente();
        clienteModificado.setIdCliente(1);
        clienteModificado.setIdUsuario(10);
        clienteModificado.setPrimerNombre("Matias");
        clienteModificado.setApellidoPaterno("Figueroa");
        clienteModificado.setTelefono("+56987654321");
        clienteModificado.setNotasAlergia("Ninguna");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteModificado);

        Cliente resultado = clienteService.updateCliente(clienteModificado);

        assertEquals("+56987654321", resultado.getTelefono());
        verify(clienteRepository, times(1)).save(clienteModificado);
    }

    // ==========================================
    // CP-18: Consultar cliente inexistente
    // ==========================================
    @Test
    void testConsultarClienteInexistenteRetornaVacio() {
        when(clienteRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Cliente> resultado = clienteService.getCliente(999);

        assertTrue(resultado.isEmpty());
        verify(clienteRepository, times(1)).findById(999);
    }
}

