CREATE TABLE IF NOT EXISTS historial_notificacion (
    id_notificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente      INT NOT NULL,
    mensaje         VARCHAR(255) NOT NULL,
    fecha_envio     DATETIME NOT NULL
);
