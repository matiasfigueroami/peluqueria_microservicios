CREATE TABLE IF NOT EXISTS roles_usu (
    id_roles_usu INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol   VARCHAR(30) NOT NULL
);

INSERT INTO roles_usu (nombre_rol) VALUES ('ADMIN'), ('CLIENTE'), ('PELUQUERO');

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(100) NOT NULL,
    password   VARCHAR(100) NOT NULL,
    activo     TINYINT(1)   NOT NULL DEFAULT 1,
    rol_id     INT          NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES roles_usu(id_roles_usu)
);
