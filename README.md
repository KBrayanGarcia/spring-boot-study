# Spring Boot Study API 🚀

Este proyecto es una API REST construida con **Spring Boot 4** y **Java 17**, diseñada como una guía práctica de transición y equivalencias desde **NestJS** y Node.js.

Incluye la gestión de tareas (`Tasks`) asociadas a usuarios (`Users`), validación de datos (DTOs), manejo global de excepciones, paginación, búsquedas personalizadas, perfiles de entorno y pruebas automatizadas.

---

## 🛠️ Requisitos Previos

*   **Java 17** o superior instalado.
*   **Maven** (no requiere instalación global, el proyecto incluye el ejecutable local `./mvnw`).

---

## 🚀 Cómo Iniciar el Servidor

El proyecto utiliza **Spring Profiles** para separar las configuraciones de desarrollo y producción.

### 1. Modo Desarrollo (Perfil por defecto - `dev`)
Usa una base de datos **H2 en memoria** y tiene la consola de base de datos habilitada.
```bash
# En Windows (PowerShell/CMD):
.\mvnw spring-boot:run

# En Linux/macOS:
./mvnw spring-boot:run
```
*   **API URL:** `http://localhost:8080`
*   **Consola H2 (Base de datos):** `http://localhost:8080/h2-console`
    *   *JDBC URL:* `jdbc:h2:mem:study_db`
    *   *Usuario:* `sa`
    *   *Contraseña:* (vacía)

### 2. Modo Producción (Perfil `prod`)
Usa una base de datos **H2 persistente en archivo** (se guarda en `./data/study_prod_db`) y deshabilita la consola de base de datos por seguridad.
```bash
# En Windows (PowerShell/CMD):
.\mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"

# En Linux/macOS:
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

---

## 🧪 Cómo Ejecutar las Pruebas (Testing)

El proyecto incluye pruebas unitarias para los servicios (usando **Mockito** en aislamiento) y pruebas de integración para los controladores (usando **MockMvc**).

Para correr la suite de pruebas completa:
```bash
# En Windows (PowerShell/CMD):
.\mvnw test

# En Linux/macOS:
./mvnw test
```

---

## 🗺️ Endpoints Principales de la API

### Tareas (`/api/tasks`)
*   `GET /api/tasks` - Retorna tareas paginadas (admite query params: `?page=0&size=3&sort=title,desc`).
*   `GET /api/tasks/{id}` - Obtiene el detalle de una tarea por su ID.
*   `GET /api/tasks/search` - Buscador personalizado (admite query params: `?completed=false&keyword=produccion`).
*   `POST /api/tasks` - Crea una tarea. Admite asociar un usuario mediante `userId` en el JSON.
*   `PUT /api/tasks/{id}` - Actualiza una tarea completa.
*   `DELETE /api/tasks/{id}` - Elimina una tarea.

### Usuarios (`/api/users`)
*   `GET /api/users` - Lista todos los usuarios con sus respectivas tareas.
*   `POST /api/users` - Registra un nuevo usuario (`{"name": "...", "email": "..."}`).
