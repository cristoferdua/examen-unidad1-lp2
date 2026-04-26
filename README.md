# examendu - Proyecto Spring Boot

## Tecnologías

- **Java 21+**
- **Spring Boot 3.2.5**
- **Gradle Kotlin DSL**
- **PostgreSQL** (Docker)
- **MapStruct** 1.5.5
- **Lombok**
- **Bean Validation** (Jakarta)

## Requisitos previos

- Java 21 instalado
- Docker y Docker Compose instalados
- IntelliJ IDEA (recomendado)

## Pasos para levantar el proyecto

### 1. Abrir el proyecto en IntelliJ

File > Open > Seleccionar la carpeta `examendu`

### 2. Activar Annotation Processing (IMPORTANTE para MapStruct + Lombok)

File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors
- Marcar **Enable annotation processing**

### 3. Esperar a que Gradle descargue dependencias

IntelliJ descargará automáticamente todas las dependencias al abrir el proyecto.

### 4. Levantar la base de datos con Docker

```bash
docker-compose up -d
```

Esto levanta un contenedor PostgreSQL con:
- Base de datos: `examendu`
- Usuario: `examendu`
- Contraseña: `examendu123`
- Puerto: `5432`

### 5. Ejecutar la aplicación

Ejecutar la clase `PeliculasApplication.java` desde IntelliJ, o:

```bash
./gradlew bootRun
```

La app arranca en `http://localhost:8080`

### 6. Probar los endpoints

En IntelliJ, abrir los archivos `.http` en la carpeta `http/` y ejecutar las peticiones.

**Orden recomendado:**
1. `genero.http` → Crear géneros primero
2. `director.http` → Crear directores
3. `pelicula.http` → Crear películas (usando IDs de directores y géneros)
4. `pelicula.http` → Probar el endpoint estrella `POST /api/peliculas/con-generos`

> Nota: Reemplaza los valores `{{directorId}}`, `{{generoId}}`, etc. con los UUID que devuelve cada POST.

## Estructura del proyecto

```
src/main/java/com/examendu/peliculas/
├── entity/          → Entidades JPA (Director, Genero, Pelicula, PeliculaGenero)
├── repository/      → Interfaces JpaRepository
├── dto/             → Data Transfer Objects (Request + Response)
├── mapper/          → Mappers MapStruct
├── service/         → Interfaces + Implementaciones
├── controller/      → Controladores REST
└── exception/       → Manejo de excepciones global
```

## Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/directores | Listar directores |
| GET | /api/directores/{id} | Buscar director |
| POST | /api/directores | Crear director |
| PUT | /api/directores/{id} | Actualizar director |
| DELETE | /api/directores/{id} | Eliminar director |
| GET | /api/generos | Listar géneros |
| GET | /api/generos/{id} | Buscar género |
| POST | /api/generos | Crear género |
| PUT | /api/generos/{id} | Actualizar género |
| DELETE | /api/generos/{id} | Eliminar género |
| GET | /api/peliculas | Listar películas (con géneros) |
| GET | /api/peliculas/{id} | Buscar película |
| POST | /api/peliculas | Crear película |
| PUT | /api/peliculas/{id} | Actualizar película |
| DELETE | /api/peliculas/{id} | Eliminar película |
| POST | /api/peliculas/con-generos | Crear película con géneros |
