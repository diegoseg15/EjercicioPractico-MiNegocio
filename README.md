# Data Management Service

Microservicio desarrollado con **Spring Boot (WebFlux + R2DBC)** para la gestión de clientes y direcciones en el sistema **MiNegocio**.  
Incluye integración con **PostgreSQL**, soporte para **Docker** y configuración flexible mediante **variables de entorno**.

---

## Requisitos previos

Con docker:
- [Docker](https://www.docker.com/) instalado

Sin docker: 
- JDK 17 (si quieres ejecutar sin Docker)  
- Maven 3.9+ (si quieres compilar manualmente)

---

## Configuración

El servicio utiliza **variables de entorno** en .env para conectarse a la base de datos.  
Estas se definen en el archivo `docker-compose.yaml`:

```yaml
environment:
  SPRING_R2DBC_URL: r2dbc:postgresql://database-minegocio:5432/${POSTGRES_DB}
  SPRING_R2DBC_USERNAME: ${DB_ADMIN_USER}
  SPRING_R2DBC_PASSWORD: ${DB_ADMIN_PASSWORD}
  SERVER_PORT: 8080
  SPRING_PROFILES_ACTIVE: prod
````

Valores por defecto en `application.properties`:

```properties
spring.r2dbc.url=r2dbc:postgresql://127.0.0.1:5433/minegocio
spring.r2dbc.username=admin
spring.r2dbc.password=123456
server.port=8086
```

---

## Ejecución con Docker

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/diegoseg15/EjercicioPractico-MiNegocio.git
   cd EjercicioPractico-MiNegocio
   ```

2. Levantar servicios:

   ```bash
   docker compose up -d --build
   ```

3. La API estará disponible en:
   👉 [http://localhost:8080](http://localhost:8080)

---

## Endpoints principales

### Clientes

* **Agregar cliente matriz**

  ```http
  POST /api/v1/data/clientes/agregar/matriz
  ```

* **Eliminar cliente**

  ```http
  DELETE /api/v1/data/clientes/eliminar/{companyId}/{identification}
  ```

* **Consultar clientes**

  ```http
  GET /api/v1/data/clientes/lista
  ```

* **Consultar clientes por nombre y/o apellido**

  ```http
  GET /api/v1/data/clientes/buscar-nombre/{name}
  ```

* **Consultar clientes por identificación (cedula, pasaporte o ruc)**

  ```http
  GET /api/v1/data/clientes/buscar-nombre/{identification}
  ```

### Direcciones

* **Agregar dirección con la matriz**

  ```http
  POST /api/v1/data/direcciones/agregar/{companyId}/{identification}
  ```

* **Listar direcciones de un cliente**

  ```http
  GET /api/v1/data/direcciones/{companyId}/{identification}
  ```

---

## Desarrollo local (sin Docker)

1. Inicia una base de datos PostgreSQL en local (puerto `5433` según properties).

2. Compila el proyecto:

   ```bash
   mvn clean package -DskipTests
   ```

3. Ejecuta la app:

   ```bash
   java -jar target/*.jar
   ```

---

## Tests

Ejecutar pruebas unitarias y de integración:

```bash
mvn test
```

---

## Nota

* El servicio está preparado para **código limpio (SOLID)** y buenas prácticas.
* Compatible con **Liquibase** para versionado de base de datos (opcional).
* En producción se recomienda configurar:

  * Variables `JAVA_OPTS` (memoria y GC).
  * Healthchecks en `docker-compose` o Kubernetes.
  * Monitoreo con Actuator.

---

## Licencia MIT
Este proyecto está licenciado bajo la MIT License. Consulta el archivo LICENSE para más detalles.

## Autor

**Diego Segovia**
Full Stack Developer | Java, Spring Boot, React, Docker

* LinkedIn: [linkedin.com/in/diegoseg15](https://linkedin.com/in/diegoseg15)
