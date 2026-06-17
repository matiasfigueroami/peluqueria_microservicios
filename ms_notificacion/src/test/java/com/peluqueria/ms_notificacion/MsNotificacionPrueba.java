package com.peluqueria.ms_notificacion;
import com.peluqueria.ms_notificacion.model.Notificacion;
import com.peluqueria.ms_notificacion.repository.NotificacionRepository;
import com.peluqueria.ms_notificacion.service.NotificacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MsNotificacionPrueba {
    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    private Notificacion notificacionValida;

    @BeforeEach
    void setUp() {
        notificacionValida = new Notificacion();
        notificacionValida.setIdCliente(1);
        notificacionValida.setMensaje("Tu cita ha sido confirmada");
        notificacionValida.setFechaEnvio(LocalDateTime.now());
    }

    // ==========================================
    // CP-22: Enviar notificación de confirmación
    // ==========================================
    @Test
    void testEnviarNotificacionDeConfirmacion_debeGuardarseCorrectamente() {
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacionValida);

        Notificacion resultado = notificacionService.saveNotificacion(notificacionValida);

        assertNotNull(resultado);
        assertTrue(resultado.getMensaje().contains("confirmada"));
        verify(notificacionRepository, times(1)).save(notificacionValida);
    }

    // ==========================================
    // CP-23: Enviar notificación de cancelación
    // ==========================================
    @Test
    void testEnviarNotificacionDeCancelacion_debeGuardarseCorrectamente() {
        Notificacion notificacionCancelacion = new Notificacion();
        notificacionCancelacion.setIdCliente(1);
        notificacionCancelacion.setMensaje("Tu cita ha sido cancelada");
        notificacionCancelacion.setFechaEnvio(LocalDateTime.now());

        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacionCancelacion);

        Notificacion resultado = notificacionService.saveNotificacion(notificacionCancelacion);

        assertNotNull(resultado);
        assertTrue(resultado.getMensaje().contains("cancelada"));
        verify(notificacionRepository, times(1)).save(notificacionCancelacion);
    }

    // ==========================================
    // CP-24: Notificación con cliente inexistente/inválido
    // ==========================================
    @Test
    void testConsultarNotificacionConClienteInexistente_noDebeRomperFlujo() {
        when(notificacionRepository.findById(999)).thenReturn(java.util.Optional.empty());

        var resultado = notificacionService.getNotificacion(999);

        assertTrue(resultado.isEmpty(), "No debe lanzar excepción, solo retornar vacío");
        verify(notificacionRepository, times(1)).findById(999);
    }
}
