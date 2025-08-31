# Prices Service - Hexagonal Architecture

This project is a technical test for price management, implemented with a professional hexagonal architecture using Spring Boot and Maven multi-module.

## Module Structure

The project is divided into 6 modules, each with a clear responsibility:

- **prices-domain**: Domain core. Contains business entities and ports (interfaces). No external dependencies.
- **prices-application**: Application services and use cases. Orchestrates business logic using domain ports. Depends on `prices-domain` and Spring Boot Starter.
- **prices-infrastructure**: Persistence adapters (JPA/H2), data initialization, and configuration. Depends on `prices-domain`, JPA, and H2.
- **prices-api**: REST controllers and endpoint configuration. Depends on `prices-application` and `prices-shared`.
- **prices-shared**: DTOs, mappers, utilities, and shared exceptions. Depends on `prices-domain` if DTOs/mappers use domain entities.
- **prices-boot**: Main class and global configuration. Depends on all previous modules and Spring Boot Starter.

## Startup & Configuration

- The main class is in `prices-boot` (`com.inditex.prices_service.boot.PricesServiceApplication`).
- Component, JPA entity, and repository scanning is configured for all relevant packages.
- The `application.properties` file and other resources are in `prices-boot/src/main/resources/`.

## Main Endpoint

- **GET /prices**
  - Parameters: `date` (ISO-8601), `productId`, `brandId`
  - Example:
    ```sh
    curl "http://localhost:8080/prices?date=2020-06-14T10:00:00&productId=35455&brandId=1"
    ```
  - Example response:
    ```json
    {
      "productId": 35455,
      "brandId": 1,
      "priceList": 1,
      "startDate": "2020-06-14T00:00:00",
      "endDate": "2020-12-31T23:59:59",
      "price": 35.50,
      "curr": "EUR"
    }
    ```

---

## Architectural Decisions & Best Practices

### Hexagonal Architecture
- The domain and use cases do not depend on technical details or frameworks.
- Input adapters (REST API) and output adapters (persistence) depend on the domain, never the other way around.
- Domain interfaces (ports) decouple business logic from technical implementations.

### Module Separation
- Each module has a single responsibility and only depends on what is necessary.
- No cyclic dependencies between modules.
- Boot and integration tests are in `prices-boot`, which brings all modules together.

### Testing Strategy
- Unit tests are located in domain and application modules, testing business logic and use cases in isolation.
- Integration tests are in `prices-boot`, starting the real Spring Boot context and validating integration between modules and adapters.
- Mocks required for integration tests are defined in the boot module and marked as `@Primary` to avoid bean conflicts.

### Best Practices
- The main class exists only in `prices-boot`.
- Test beans and configuration are properly isolated.
- Use Spring profiles to separate test and production contexts.
- Extend with TestContainers for integration tests with real databases.
- The architecture is extensible and maintainable.

### Extensibility & Quality
- The project is ready for continuous integration (CI/CD) and automated validations.
- Documentation and structure facilitate onboarding for new developers.

---

For any questions about architecture, decisions, or extensions, refer to this README or contact the technical team.
