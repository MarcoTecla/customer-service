# Customer Service

Microservicio responsable de administrar clientes. Expone una API REST y persiste la información con Spring Data JPA y H2.

## Tecnologías
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Bean Validation

## Configuración
Puerto: `8081`.

Configuración `application.yml`:

```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:h2:file:./data/customers;DB_CLOSE_DELAY=-1;MODE=PostgreSQL
    username: sa
    password:
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## Ejecutar
Desde la carpeta del microservicio:

`mvn clean test`

`mvn spring-boot:run`.

## Endpoints principales
Base URL: `http://localhost:8081`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/customers` | Lista clientes |
| GET | `/api/customers/{id}` | Consulta un cliente por ID |
| POST | `/api/customers` | Crea un cliente |
| PUT | `/api/customers/{id}` | Actualiza un cliente |
| DELETE | `/api/customers/{id}` | Elimina un cliente |

### Ejemplo de creación
```http
POST http://localhost:8081/api/customers
Content-Type: application/json
```

```json
{
  "name": "Juan Perez",
  "email": "juan.perez@example.com"
}
```

