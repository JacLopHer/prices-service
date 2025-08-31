# Prices Service - Hexagonal Architecture

Este proyecto es una prueba técnica para la gestión de precios, implementada con arquitectura hexagonal profesional usando Spring Boot y Maven multi-módulo.

## Estructura de Módulos

El proyecto está dividido en 6 módulos, cada uno con una responsabilidad clara:

- **prices-domain**: Dominio central. Contiene entidades de negocio y puertos (interfaces). Sin dependencias externas.
- **prices-application**: Servicios y casos de uso de la aplicación. Orquesta la lógica de negocio usando los puertos del dominio. Depende de `prices-domain` y Spring Boot Starter.
- **prices-infrastructure**: Adaptadores de persistencia (JPA/H2), inicialización de datos y configuración. Depende de `prices-domain`, JPA y H2.
- **prices-api**: Controladores REST y configuración de endpoints. Depende de `prices-application` y `prices-shared`.
- **prices-shared**: DTOs, mapeadores, utilidades y excepciones compartidas. Depende de `prices-domain` si los DTOs/mapeadores utilizan entidades del dominio.
- **prices-boot**: Clase principal y configuración global. Depende de todos los módulos anteriores y de Spring Boot Starter.

## Inicio y Configuración

- La clase principal se encuentra en `prices-boot` (`com.inditex.prices_service.boot.PricesServiceApplication`).
- El escaneo de componentes, entidades JPA y repositorios está configurado para todos los paquetes relevantes.
- El archivo `application.properties` y otros recursos están en `prices-boot/src/main/resources/`.

## Endpoint Principal

- **GET /prices**
  - Parámetros: `date` (ISO-8601 con zona horaria, ej: `2020-06-14T10:00:00+02:00`), `productId`, `brandId`
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
| 2020-06-16T21:00:00+02:00  | 35455     | 1       | 4         | 2020-06-15T16:00:00+02:00| 2020-12-31T23:59:59+02:00| 38.95  | EUR      |

> **Nota:** La base de datos H2 se inicializa automáticamente con los datos de ejemplo al arrancar la aplicación.

---

## Decisiones Arquitectónicas y Mejores Prácticas

### Arquitectura Hexagonal
- El dominio y los casos de uso no dependen de detalles técnicos o frameworks.
- Los adaptadores de entrada (API REST) y los adaptadores de salida (persistencia) dependen del dominio, nunca al revés.
- Las interfaces del dominio (puertos) desacoplan la lógica de negocio de las implementaciones técnicas.

### Separación de Módulos
- Cada módulo tiene una única responsabilidad y solo depende de lo que es necesario.
- No hay dependencias cíclicas entre módulos.
- Las pruebas de arranque e integración están en `prices-boot`, que reúne todos los módulos.

### Estrategia de Pruebas
- Las pruebas unitarias se encuentran en los módulos de dominio y aplicación, probando la lógica de negocio y los casos de uso en aislamiento.
- Las pruebas de integración están en `prices-boot`, iniciando el verdadero contexto de Spring Boot y validando la integración entre módulos y adaptadores.
- Los mocks requeridos para las pruebas de integración se definen en el módulo boot y se marcan como `@Primary` para evitar conflictos de beans.

### Mejores Prácticas
- La clase principal existe solo en `prices-boot`.
- Los beans y la configuración de prueba están debidamente aislados.
- Se utilizan perfiles de Spring para separar los contextos de prueba y producción.
- Extender con TestContainers para pruebas de integración con bases de datos reales.
- La arquitectura es extensible y mantenible.

### Extensibilidad y Calidad
- El proyecto está listo para la integración continua (CI/CD) y validaciones automáticas.
- La documentación y la estructura facilitan la incorporación de nuevos desarrolladores.

---

Para cualquier pregunta sobre la arquitectura, decisiones o extensiones, consulte este README o contacte al equipo técnico.
