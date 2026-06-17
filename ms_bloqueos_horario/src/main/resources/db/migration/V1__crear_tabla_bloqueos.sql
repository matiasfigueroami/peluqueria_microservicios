CREATE TABLE IF NOT EXISTS bloqueo_horario (
     id_bloqueo_horario INT AUTO_INCREMENT PRIMARY KEY,
     id_empleado INT NOT NULL,
     fecha_hora_inicio DATETIME NOT NULL,
     fecha_hora_fin DATETIME NOT NULL,
     motivo VARCHAR(255) NOT NULL
);