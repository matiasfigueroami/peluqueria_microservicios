CREATE TABLE IF NOT EXISTS categoria (
               id_categoria INT AUTO_INCREMENT PRIMARY KEY,
               nombre_categoria VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS servicio (
    id_servicio INT AUTO_INCREMENT PRIMARY KEY,
    id_categoria INT NOT NULL,
    nombre_servicio VARCHAR(100) NOT NULL,
    precio INT NOT NULL,
    duracion_minutos_aprox VARCHAR(60) NOT NULL,
    CONSTRAINT fk_servicio_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE CASCADE ON UPDATE CASCADE
);