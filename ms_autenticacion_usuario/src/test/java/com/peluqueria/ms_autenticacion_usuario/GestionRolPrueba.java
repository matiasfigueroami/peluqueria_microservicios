package com.peluqueria.ms_autenticacion_usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peluqueria.ms_autenticacion_usuario.model.Rol;
import com.peluqueria.ms_autenticacion_usuario.model.Usuario;
import com.peluqueria.ms_autenticacion_usuario.repository.RolRepository;
import com.peluqueria.ms_autenticacion_usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GestionRolPrueba {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
        rolRepository.deleteAll();
    }

    // CP-10: Registro de rol nuevo
    @Test
    void CP10_registroRolNuevo_debeRetornar201YIdGenerado() throws Exception {
        Rol rolValido = new Rol();
        rolValido.setNombreRol("ADMIN");

        MvcResult result = mockMvc.perform(post("/api/v1/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rolValido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idRol").exists())
                .andExpect(jsonPath("$.nombreRol").value("ADMIN"))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        Rol rolCreado = objectMapper.readValue(responseContent, Rol.class);

        assertNotNull(rolCreado.getIdRol(), "El ID del rol debe ser generado");
        assertTrue(rolRepository.existsById(rolCreado.getIdRol()), "El rol debe existir en la BD");
    }

    // CP-11: Eliminar rol con usuarios asociados
    @Test
    void CP11_eliminarRolConUsuariosAsociados_debeFallar() throws Exception {
        Rol rol = new Rol();
        rol.setNombreRol("ADMIN");
        rol = rolRepository.save(rol);

        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password123");
        usuario.setActivo(true);
        usuario.setRol(rol);
        usuarioRepository.save(usuario);

        mockMvc.perform(delete("/api/v1/roles/{id}", rol.getIdRol())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        assertTrue(rolRepository.existsById(rol.getIdRol()), "El rol no debe haberse eliminado");
    }
}