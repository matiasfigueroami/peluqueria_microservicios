CREATE TABLE IF NOT EXISTS cliente (
     id INT AUTO_INCREMENT PRIMARY KEY,
     usuario_id INT NOT NULL,
     primer_nombre VARCHAR(60) NOT NULL,
     apellido_paterno VARCHAR(60) NOT NULL,
     telefono VARCHAR(20) NOT NULL,
     notas_alergia varchar(255) null
);