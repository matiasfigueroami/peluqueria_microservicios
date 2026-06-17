# Plan de pruebas

## Objetivo

Validar el correcto funcionamiento de los microservicios del sistema de Gestión Peluquería.

## Herramientas

* JUnit
* DataFaker

---

## Casos de prueba

### ms_agenda_cita

#### CP-01
**Registro de agenda cita**
Verificar que una agenda se pueda crear correctamente con datos válidos.
Tipo: Unitaria



#### CP-04
**Conflicto de horario en cita**
Intentar crear una cita en un horario ya ocupado y esperar error de conflicto.
Tipo: Negativo

#### CP-05
**Agendar con peluquero bloqueado**
Intentar agendar cuando el peluquero tiene un bloqueo activo en ese horario.
Tipo: Negativo

---

### ms_autenticacion_usuario

#### CP-06
**Registro de usuario nuevo**
Enviar una petición POST a `/api/v1/usuarios` con email, password, activo y rol válidos, y verificar que retorne HTTP 201 (Created) con el usuario guardado y su `idUsuario` generado.
Tipo: Integración

#### CP-07
**Registro con email inválido**
Enviar una petición POST a `/api/v1/usuarios` con un email mal formado y verificar que retorne HTTP 400 (Bad Request) por violar la validación `@Email`.
Tipo: Negativo

#### CP-08
**Consultar usuario por ID inexistente**
Enviar una petición GET a `/api/v1/usuarios/{id}` con un ID que no existe y verificar que retorne HTTP 404 (Not Found).
Tipo: Negativo

#### CP-09
**Actualizar usuario existente**
Enviar una petición PUT a `/api/v1/usuarios/{id}` con datos modificados (ej. password o estado `activo`) y verificar que retorne HTTP 200 (OK) y los cambios persistan.
Tipo: Unitaria

#### CP-10
**Registro de rol nuevo**
Enviar una petición POST a `/api/v1/roles` con `nombreRol` válido y verificar que retorne HTTP 201 (Created) con el rol guardado y su `idRol` generado.
Tipo: Unitaria

#### CP-11
**Eliminar rol con usuarios asociados**
Verificar el comportamiento del sistema (cascada o error de integridad) al intentar eliminar un rol que aún tiene usuarios vinculados.
Tipo: Negativo / Integración

---

### ms_bloqueos_horario

#### CP-12
**Crear bloqueo de horario**
Verificar que un peluquero pueda registrar un bloqueo en un rango de fechas dado.
Tipo: Unitaria

#### CP-13
**Listar bloqueos activos**
Verificar que se retornen solo los bloqueos vigentes para un peluquero.
Tipo: Unitaria

#### CP-14
**Bloqueo con fecha pasada**
Intentar crear un bloqueo con fecha inicio en el pasado y esperar error de validación.
Tipo: Negativo

#### CP-15
**Bloqueo impide creación de cita**
Verificar que ms_agenda_cita rechace una cita cuando ms_bloqueos_horario retorna bloqueo activo.
Tipo: Integración

---

### ms_clientes_perfil

#### CP-16
**Crear perfil de cliente**
Verificar que se guarde correctamente el perfil con nombre, teléfono y email.
Tipo: Unitaria

#### CP-17
**Actualizar datos del perfil**
Verificar que los cambios en nombre o teléfono se persistan correctamente.
Tipo: Unitaria

#### CP-18
**Consultar cliente inexistente**
Buscar un cliente con ID que no existe y esperar 404 Not Found.
Tipo: Negativo

---

### ms_especialidades_catalogo

#### CP-19
**Listar especialidades**
Verificar que se retorne el listado completo de especialidades disponibles.
Tipo: Unitaria

#### CP-20
**Crear nueva especialidad**
Verificar que una especialidad con nombre y descripción se persista correctamente.
Tipo: Unitaria

#### CP-21
**Especialidad con nombre duplicado**
Intentar crear una especialidad con nombre ya existente y esperar error.
Tipo: Negativo

---

### ms_notificacion

#### CP-22
**Enviar notificación de confirmación**
Verificar que al crear una cita se dispare la notificación de confirmación al cliente.
Tipo: Integración

#### CP-23
**Enviar notificación de cancelación**
Verificar que al cancelar una cita se notifique correctamente al cliente.
Tipo: Integración

#### CP-24
**Notificación con email inválido**
Intentar enviar notificación a email malformado y registrar el error sin romper el flujo.
Tipo: Negativo

---

### ms_pago_boleta

#### CP-25
**Registrar pago de servicio**
Verificar que un pago quede registrado con monto, método y referencia de cita.
Tipo: Unitaria

#### CP-26
**Generar boleta PDF**
Verificar que se genere una boleta con los datos correctos del servicio pagado.
Tipo: Unitaria

#### CP-27
**Pago con monto negativo**
Intentar registrar un pago con monto menor o igual a 0 y esperar error de validación.
Tipo: Negativo

#### CP-28
**Consultar historial de pagos por cliente**
Verificar que se retornen todos los pagos asociados a un cliente ordenados por fecha.
Tipo: Unitaria

---

### ms_peluqueros_staff

#### CP-29
**Registrar nuevo peluquero**
Verificar que un peluquero se cree con nombre, especialidades y disponibilidad.
Tipo: Unitaria

#### CP-30
**Listar peluqueros disponibles**
Verificar que se retornen solo peluqueros activos y sin bloqueos en el rango indicado.
Tipo: Integración

#### CP-31
**Actualizar especialidades de peluquero**
Verificar que la lista de especialidades se actualice correctamente.
Tipo: Unitaria

#### CP-32
**Peluquero inexistente en cita**
Intentar agendar con ID de peluquero que no existe y esperar 404 Not Found.
Tipo: Negativo

---

### ms_resenas_feedback

#### CP-33
**Crear reseña de servicio**
Verificar que un cliente pueda dejar una reseña con puntuación 1-5 y comentario.
Tipo: Unitaria

#### CP-34
**Listar reseñas por peluquero**
Verificar que se retornen todas las reseñas de un peluquero ordenadas por fecha.
Tipo: Unitaria

#### CP-35
**Reseña con puntuación fuera de rango**
Intentar crear reseña con rating igual a 6 o 0 y esperar error de validación.
Tipo: Negativo

#### CP-36
**Reseña sin cita completada**
Intentar dejar reseña de un servicio no completado y esperar error de regla de negocio.
Tipo: Negativo

---

### ms_servicios_precio

#### CP-37
**Crear servicio con precio**
Verificar que un servicio se registre con nombre, descripción y precio.
Tipo: Unitaria

#### CP-38
**Actualizar precio de servicio**
Verificar que el precio se actualice y quede registrado el historial de cambio.
Tipo: Unitaria

#### CP-39
**Precio negativo o cero**
Intentar crear un servicio con precio menor o igual a 0 y esperar error de validación.
Tipo: Negativo

#### CP-40
**Listar servicios activos**
Verificar que solo se retornen servicios con estado activo.
Tipo: Unitaria