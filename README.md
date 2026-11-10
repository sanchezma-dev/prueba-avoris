# prueba-avoris

Proyecto Spring Boot generado desde `spring-boot-hexagonal-archetype`.

- Java 21
- Maven 3.8+

```bash
mvn spring-boot:run
```

- `application/domain`: modelo de dominio.
- `application/ports/in`: contrato de entrada del caso de uso.
- `application/ports/out`: contrato de salida para persistencia.
- `application/services`: implementación del caso de uso.
- `infrastructure/apirest/adapters`: adaptador HTTP/controller.
- `infrastructure/sql/adapters`: adaptador de persistencia.
- `infrastructure/sql/entities`: entidad JPA.
