# Prices Service - Hexagonal Architecture

## Descripción General
Este proyecto es una prueba técnica para la gestión de precios, implementada con arquitectura hexagonal profesional usando Spring Boot y Maven multi-módulo. Permite consultar precios de productos en función de la fecha, el producto y la marca.

## Objetivo
Demostrar buenas prácticas de diseño, arquitectura y desarrollo en Java, aplicando principios SOLID y patrones de arquitectura hexagonal.

## Tecnologías Utilizadas
- Java 17+
- Spring Boot
- Maven multi-módulo
- JPA/Hibernate
- H2 Database (memoria)
- REST API
- JUnit y Mockito (testing)

## Estructura de Módulos
El proyecto está dividido en 6 módulos, cada uno con una responsabilidad clara:
- **prices-domain**: Dominio central. Entidades de negocio y puertos (interfaces). Sin dependencias externas.
- **prices-application**: Servicios y casos de uso. Orquesta la lógica de negocio usando los puertos del dominio.
- **prices-infrastructure**: Adaptadores de persistencia (JPA/H2), inicialización de datos y configuración.
- **prices-api**: Controladores REST y configuración de endpoints.
- **prices-shared**: DTOs, mapeadores, utilidades y excepciones compartidas.
- **prices-boot**: Clase principal y configuración global.

## Instalación y Ejecución
1. Clona el repositorio:
   ```sh
   git clone <url-del-repositorio>
   ```
2. Compila el proyecto:
   ```sh
   ./mvnw clean install
   ```
3. Ejecuta la aplicación:
   ```sh
   ./mvnw spring-boot:run -pl prices-boot
   ```
4. Accede al endpoint principal en:
   `http://localhost:8080/prices`

## Ejecución de Pruebas
Para ejecutar los tests:
```sh
./mvnw test
```
Los reportes de pruebas se encuentran en las carpetas `target/surefire-reports/` de cada módulo.

## Endpoint Principal
- **GET /prices**
  - Parámetros: `date` (ISO-8601 con zona horaria), `productId`, `brandId`
  - Ejemplo:
    ```sh
    curl "http://localhost:8080/prices?date=2020-06-14T10:00:00+02:00&productId=35455&brandId=1"
    ```
  - Ejemplo de respuesta:
    ```json
    {
      "productId": 35455,
      "brandId": 1,
      "priceList": 1,
      "startDate": "2020-06-14T00:00:00+02:00",
      "endDate": "2020-12-31T23:59:59+02:00",
      "price": 35.50,
      "currency": "EUR"
    }
    ```

### Casos de ejemplo y respuestas esperadas
| Fecha de consulta           | productId | brandId | priceList | startDate                | endDate                  | price  | currency |
|----------------------------|-----------|---------|-----------|--------------------------|--------------------------|--------|----------|
| 2020-06-14T10:00:00+02:00  | 35455     | 1       | 1         | 2020-06-14T00:00:00+02:00| 2020-12-31T23:59:59+02:00| 35.50  | EUR      |
| 2020-06-14T16:00:00+02:00  | 35455     | 1       | 2         | 2020-06-14T15:00:00+02:00| 2020-06-14T18:30:00+02:00| 25.45  | EUR      |
| 2020-06-14T21:00:00+02:00  | 35455     | 1       | 1         | 2020-06-14T00:00:00+02:00| 2020-12-31T23:59:59+02:00| 35.50  | EUR      |
| 2020-06-15T10:00:00+02:00  | 35455     | 1       | 3         | 2020-06-15T00:00:00+02:00| 2020-06-15T11:00:00+02:00| 30.50  | EUR      |

## Estructura de Carpetas
```
prices-service/
├── prices-domain/
├── prices-application/
├── prices-infrastructure/
├── prices-api/
├── prices-shared/
├── prices-boot/
```

## Contribución
Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request.

## Contacto
Autor: [Tu Nombre]
Email: [tu.email@dominio.com]

## Licencia
Este proyecto se distribuye bajo la licencia MIT.
