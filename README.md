# SpringBoot - API REST
Este proyecto es una API REST realizada con Java y Spring  que permite agregar, eliminar, actualizar y recuperar estudiantes de una base de datos PostgreSQL. Se utilizó Hibernate como ORM para el mapeo de objeto. Se aplicaron patrones de diseño como DAO e Inyección de dependencias.
Tambien se agregaron pruebas unitarias que se ejecutan contra una base de datos H2(in-memory-data base).

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data
- JPA y Hibernate
- PostgreSQL
- Swagger/OpenAPI

## Endpoints API


### Obtener todos los estudiantes

Para obtener todos los estudiantes, se puede hacer una petición **GET** a `api/vi/students`.
Ejemplo de respuesta
<img width="470" alt="image" src="https://raw.githubusercontent.com/flosardo/SpringBootAPI/main/assets/peticionGET.png">



### Agregar un nuevo estudiante

Para agregar un nuevo estudiante, se puede hacer una petición **POST** a `api/vi/students` con el cuerpo de la petición conteniendo los detalles del estudiante.

### Eliminar un estudiante

Para eliminar un estudiante, se puede hacer una petición **DELETE** a `api/vi/students/{id}`, donde `{id}` es el ID del estudiante.

### Actualizar un estudiante

Para actualizar un estudiante, se puede hacer una petición **PUT** a `api/vi/students/{id}`, donde `{id}` es el ID del estudiante. El cuerpo de la petición debe contener los detalles actualizados del estudiante.

### Obtener un estudiante

Para obtener los detalles de un estudiante, se puede hacer una petición **GET** a `api/vi/students/{id}`, donde `{id}` es el ID del estudiante.

## Documentación de la API

La documentación de la API está disponible en `/swagger-ui.html`.

## Cómo ejecutar el proyecto

Para ejecutar el proyecto, necesitas tener instalado Java 17 y Maven. Luego, puedes clonar el repositorio y ejecutar el siguiente comando en la raíz del proyecto:

```bash
mvn spring-boot:run
```
Esto iniciará la aplicación en el puerto 8080.