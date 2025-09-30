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

  POST /api/v1/data/clientes/agregar/matriz

  ```http
  http://localhost:8080/api/v1/data/clientes/agregar/matriz
  ```
  Body:
  
  ```json
  {
    "customer": {
        "companyId": 2,
        "identification": "1701342275",
        "identificationType": "CEDULA",
        "name": "Juan",
        "lastname": "Sanchez",
        "email": "mario.andrade@gmail.com",
        "phone": "0987654333"
    },
    "address": {
        "alias": "Matriz",
        "street": "Av. Cevallo y Quito",
        "city": "Latacunga",
        "province": "Tungurahua",
        "country": "Ecuador",
        "zip": "180101", 
        "isHeadquarters": true
    }
  }
  ```
* **Eliminar cliente**

  DELETE /api/v1/data/clientes/eliminar/{companyId}/{identification}

  ```http
  http://localhost:8080/api/v1/data/clientes/eliminar/2/1701342275
  ```

* **Consultar clientes**

  GET /api/v1/data/clientes/lista

  ```http
  http://localhost:8080/api/v1/data/clientes/lista
  ```

* **Consultar clientes por nombre y/o apellido**

  GET /api/v1/data/clientes/buscar-nombre/{name}

  ```http
  http://localhost:8080/api/v1/data/clientes/buscar-nombre/Andrade
  ```

* **Consultar clientes por identificación (cedula, pasaporte o ruc)**

  GET /api/v1/data/clientes/identificacion/{identification}

  ```http
  http://localhost:8080/api/v1/data/clientes/identificacion/1799999999001
  ```

### Direcciones

* **Agregar dirección con la matriz**

  POST /api/v1/data/direcciones/agregar/{companyId}/{identification}

  ```http
  http://localhost:8080/api/v1/data/direcciones/agregar/1/AB1234567
  ```
  Body:
  
  ```json
  {
    "customerId": 2,
    "alias": "direccion2",
    "street": "Av. Amazonas y Colón",
    "city": "Quito",
    "province": "Pichincha",
    "country": "Ecuador",
    "zip": "170101",
    "isHeadquarters": false
  }
  ```

* **Listar direcciones de un cliente**

  GET /api/v1/data/direcciones/{companyId}/{identification}

  ```http
  http://localhost:8080/api/v1/data/direcciones/identificacion/1/1799999999001
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
