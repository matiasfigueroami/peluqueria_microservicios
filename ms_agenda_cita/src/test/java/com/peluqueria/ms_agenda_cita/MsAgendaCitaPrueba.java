package com.peluqueria.ms_agenda_cita;

import com.peluqueria.ms_agenda_cita.model.Cita;
import com.peluqueria.ms_agenda_cita.repository.CitaRepository;
import com.peluqueria.ms_agenda_cita.service.CitaService;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MsAgendaCitaPrueba {

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private CitaService citaService;

    private Cita cita;

    // Instanciamos Faker
    private Faker faker;

    @BeforeEach
    void setUp() {
        faker = new Faker();

        // Generamos datos dinámicos y realistas con DataFaker
        Integer idCita = faker.number().numberBetween(1, 1000);
        Integer idCliente = faker.number().numberBetween(100, 999);
        Integer idEmpleado = faker.number().numberBetween(10, 50);
        Integer idServicio = faker.number().numberBetween(1, 20);

        // Generamos una fecha en el futuro (entre 1 y 30 días adelante),
        // en un horario comercial aleatorio (entre las 9:00 y las 18:00 hrs)
        LocalDateTime fechaHoraCita = LocalDateTime.now()
                .plusDays(faker.number().numberBetween(1, 30))
                .withHour(faker.number().numberBetween(9, 18))
                .withMinute(0).withSecond(0).withNano(0);

        // Configuramos la cita válida para usar en las pruebas con los datos fakes
        cita = new Cita(
                idCita,
                idCliente,
                idEmpleado,
                idServicio,
                fechaHoraCita,
                "PROGRAMADA"
        );
    }

    // ==========================================
    // CP-01: Registro de agenda cita
    // ==========================================
    @Test
    void testCrearCitaExitosamente() {
        // Simular que no hay solapamientos ni bloqueos
        when(citaRepository.findCitasSolapadas(anyInt(), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());
        when(citaRepository.existeBloqueoEnHorario(anyInt(), any(LocalDateTime.class)))
                .thenReturn(0);

        // Simular el guardado exitoso
        when(citaRepository.save(any(Cita.class))).thenReturn(cita);

        Cita resultado = citaService.saveCita(cita);

        assertNotNull(resultado);
        assertEquals("PROGRAMADA", resultado.getEstado());
        // Verificamos que los datos generados por Faker se mantengan en el resultado
        assertEquals(cita.getIdCliente(), resultado.getIdCliente());

        verify(citaRepository, times(1)).save(cita);
    }

    // ==========================================
    // CP-04: Conflicto de horario en cita
    // ==========================================
    @Test
    void testCrearCitaConConflictoDeHorarioLanzaExcepcion() {
        // Simular que ya existe una cita en ese horario
        when(citaRepository.findCitasSolapadas(anyInt(), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(cita));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            citaService.saveCita(cita);
        });

        assertTrue(exception.getMessage().contains("El empleado ya tiene una cita agendada"));
        verify(citaRepository, never()).save(any(Cita.class));
    }

    // ==========================================
    // CP-05: Agendar con peluquero bloqueado
    // ==========================================
    @Test
    void testCrearCitaConPeluqueroBloqueadoLanzaExcepcion() {
        // Simular que no hay solapamientos de citas normales...
        when(citaRepository.findCitasSolapadas(anyInt(), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());
        // ...pero simular que hay un bloqueo de horario (retorna 1 o mayor)
        when(citaRepository.existeBloqueoEnHorario(anyInt(), any(LocalDateTime.class)))
                .thenReturn(1);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            citaService.saveCita(cita);
        });

        assertTrue(exception.getMessage().contains("El empleado tiene un bloqueo de horario"));
        verify(citaRepository, never()).save(any(Cita.class));
    }
}