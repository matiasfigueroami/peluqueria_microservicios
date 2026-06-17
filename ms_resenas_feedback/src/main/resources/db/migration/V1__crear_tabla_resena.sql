CREATE TABLE IF NOT EXISTS resena (
    id_resena INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado INT NOT NULL,
    id_cita INT NOT NULL,
    estrella INT NOT NULL,
    comentario VARCHAR(255) NOT NULL
);