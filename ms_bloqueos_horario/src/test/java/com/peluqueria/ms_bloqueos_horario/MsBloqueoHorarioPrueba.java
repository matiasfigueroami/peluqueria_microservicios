package com.peluqueria.ms_bloqueos_horario;

import com.peluqueria.ms_bloqueos_horario.model.BloqueoHorario;
import com.peluqueria.ms_bloqueos_horario.repository.BloqueoHorarioRepository;
import com.peluqueria.ms_bloqueos_horario.service.BloqueoHorarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MsBloqueoHorarioPrueba {
    @Mock
    private BloqueoHorarioRepository bloqueoHorarioRepository;

    @InjectMocks
    private BloqueoHorarioService bloqueoHorarioService;

    private BloqueoHorario bloqueoValido;

    @BeforeEach
    void setUp() {
        bloqueoValido = new BloqueoHorario();
        bloqueoValido.setIdEmpleado(1);
        bloqueoValido.setFechaHoraInicio(LocalDateTime.now().plusDays(1));
        bloqueoValido.setFechaHoraFin(LocalDateTime.now().plusDays(1).plusHours(2));
        bloqueoValido.setMotivo("Vacaciones");
    }

    // ==========================================
    // CP-12: Crear bloqueo de horario
    // ==========================================
    @Test
    void testCrearBloqueoDeHorarioExitoso() {
        when(bloqueoHorarioRepository.findSolapados(
                anyInt(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());

        when(bloqueoHorarioRepository.save(any(BloqueoHorario.class))).thenReturn(bloqueoValido);

        BloqueoHorario resultado = bloqueoHorarioService.saveBloqueoHorario(bloqueoValido);

        assertNotNull(resultado);
        assertEquals("Vacaciones", resultado.getMotivo());
        verify(bloqueoHorarioRepository, times(1)).save(bloqueoValido);
    }

    @Test
    void testCrearBloqueoDeHorarioConSolapamientoLanzaExcepcion() {
        when(bloqueoHorarioRepository.findSolapados(
                anyInt(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(new BloqueoHorario()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bloqueoHorarioService.saveBloqueoHorario(bloqueoValido);
        });

        assertTrue(exception.getMessage().contains("El empleado ya tiene un bloqueo en ese horario"));
        verify(bloqueoHorarioRepository, never()).save(any(BloqueoHorario.class));
    }

    // ==========================================
    // CP-13: Listar bloqueos activos
    // ==========================================
    @Test
    void testListarBloqueosActivos() {
        when(bloqueoHorarioRepository.findAll()).thenReturn(List.of(bloqueoValido));

        List<BloqueoHorario> resultados = bloqueoHorarioService.getBloqueoHorario();

        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());
        verify(bloqueoHorarioRepository, times(1)).findAll();
    }

    // ==========================================
    // CP-14: Bloqueo con fecha pasada
    // ==========================================
    @Test
    void testBloqueoConFechaPasadaLanzaExcepcion() {
        BloqueoHorario bloqueoPasado = new BloqueoHorario();
        bloqueoPasado.setIdEmpleado(1);
        bloqueoPasado.setFechaHoraInicio(LocalDateTime.now().minusDays(1));
        bloqueoPasado.setFechaHoraFin(LocalDateTime.now().minusDays(1).plusHours(2));
        bloqueoPasado.setMotivo("Olvide registrar esto");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bloqueoHorarioService.saveBloqueoHorario(bloqueoPasado);
        });

        assertEquals("La fecha de inicio no puede estar en el pasado", exception.getMessage());
        verify(bloqueoHorarioRepository, never()).save(any());
    }
}

