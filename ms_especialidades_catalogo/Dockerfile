# Version de Java
FROM eclipse-temurin:17-jdk-alpine

# Carpeta interna del contenedor
WORKDIR /app

# Copiamos el .jar que acabamos de compilar y le ponemos un nombre más corto
COPY target/ms_especialidades_catalogo-0.0.1-SNAPSHOT.jar app.jar

# Puerto del microservicio
EXPOSE 8087

# El comando que ejecutará Docker al encender
ENTRYPOINT ["java", "-jar", "app.jar"]

# Build
# docker build -t ms-especialidades-catalogo .

# Ejecucion
# docker run -p 8087:8087 --add-host=host.docker.internal:host-gateway -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/ms_especialidades_catalogo ms-especialidades-catalogo