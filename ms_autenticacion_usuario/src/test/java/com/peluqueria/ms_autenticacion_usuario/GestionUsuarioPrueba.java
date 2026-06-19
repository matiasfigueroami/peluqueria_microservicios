package com.peluqueria.ms_autenticacion_usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peluqueria.ms_autenticacion_usuario.model.Rol;
import com.peluqueria.ms_autenticacion_usuario.model.Usuario;
import com.peluqueria.ms_autenticacion_usuario.repository.RolRepository;
import com.peluqueria.ms_autenticacion_usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)

class GestionUsuarioPrueba {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    private Usuario usuarioValido;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
        rolRepository.deleteAll();

        Rol rol = new Rol();
        rol.setNombreRol("USER");
        rol = rolRepository.save(rol);

        usuarioValido = new Usuario();
        usuarioValido.setEmail("test@example.com");
        usuarioValido.setPassword("password123");
        usuarioValido.setActivo(true);
        usuarioValido.setRol(rol);
    }

    // CP-06: Registro de usuario nuevo
    @Test
    void CP06_registroUsuarioNuevo_debeRetornar201YIdGenerado() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioValido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUsuario").exists())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.activo").value(true))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        Usuario usuarioCreado = objectMapper.readValue(responseContent, Usuario.class);

        assertNotNull(usuarioCreado.getIdUsuario(), "El ID del usuario debe ser generado");
        assertTrue(usuarioRepository.existsById(usuarioCreado.getIdUsuario()), "El usuario debe existir en la BD");
    }

    // CP-07: Registro con email inválido
    @Test
    void CP07_registroConEmailInvalido_debeRetornar400() throws Exception {
        usuarioValido.setEmail("email_invalido");

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioValido)))
                .andExpect(status().isBadRequest());
    }

    // CP-08: Consultar usuario por ID inexistente
    @Test
    void CP08_consultarUsuarioPorIdInexistente_debeRetornar404() throws Exception {
        mockMvc.perform(get("/api/v1/usuarios/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // CP-09: Actualizar usuario existente
    @Test
    void CP09_actualizarUsuarioExistente_debeRetornar200YPersistirCambios() throws Exception {
        // Primero creamos un usuario
        MvcResult createResult = mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioValido)))
                .andExpect(status().isCreated())
                .andReturn();

        String createResponse = createResult.getResponse().getContentAsString();
        Usuario usuarioCreado = objectMapper.readValue(createResponse, Usuario.class);
        Integer id = usuarioCreado.getIdUsuario();

        // Modificamos datos
        Usuario usuarioModificado = new Usuario();
        usuarioModificado.setEmail("modificado@example.com");
        usuarioModificado.setPassword("nuevoPassword");
        usuarioModificado.setActivo(false);
        usuarioModificado.setRol(usuarioValido.getRol());

        // Actualizamos
        mockMvc.perform(put("/api/v1/usuarios/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioModificado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(id))
                .andExpect(jsonPath("$.email").value("modificado@example.com"))
                .andExpect(jsonPath("$.password").value("nuevoPassword"))
                .andExpect(jsonPath("$.activo").value(false));

        // Verificamos persistencia en BD
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id);
        assertTrue(usuarioBD.isPresent());
        assertEquals("modificado@example.com", usuarioBD.get().getEmail());
        assertEquals("nuevoPassword", usuarioBD.get().getPassword());
        assertFalse(usuarioBD.get().isActivo());
    }
}
