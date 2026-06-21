# Usamos Java 17, que es la versión de tu proyecto
FROM eclipse-temurin:17-jdk-alpine

# Carpeta interna del contenedor
WORKDIR /app

# Copiamos el .jar que acabamos de compilar y le ponemos un nombre más corto
COPY target/ms_agenda_cita-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto de este microservicio (Ajusta esto si usas otro puerto)
EXPOSE 8083

# El comando que ejecutará Docker al encender
ENTRYPOINT ["java", "-jar", "app.jar"]