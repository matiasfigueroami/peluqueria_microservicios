CREATE TABLE IF NOT EXISTS pago (
      id_pago INT AUTO_INCREMENT PRIMARY KEY,
      id_cita INT NOT NULL,
      monto_total INT NOT NULL,
      metodo_pago VARCHAR(30) NOT NULL,
      fecha_pago DATETIME NOT NULL
);