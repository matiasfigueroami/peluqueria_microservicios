CREATE TABLE IF NOT EXISTS cita (
    id_cita        INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente     INT NOT NULL,
    id_empleado    INT NOT NULL,
    id_servicio    INT NOT NULL,
    fecha_hora_inicio DATETIME NOT NULL,
    estado         VARCHAR(50) NOT NULL
);