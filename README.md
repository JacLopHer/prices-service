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

## Recommended Integration Tests

Test the following cases to validate business logic:

1. **2020-06-14 10:00**
   ```sh
   curl "http://localhost:8080/prices?date=2020-06-14T10:00:00&productId=35455&brandId=1"
   ```
2. **2020-06-14 16:00**
   ```sh
   curl "http://localhost:8080/prices?date=2020-06-14T16:00:00&productId=35455&brandId=1"
   ```
3. **2020-06-14 21:00**
   ```sh
   curl "http://localhost:8080/prices?date=2020-06-14T21:00:00&productId=35455&brandId=1"
   ```
4. **2020-06-15 10:00**
   ```sh
   curl "http://localhost:8080/prices?date=2020-06-15T10:00:00&productId=35455&brandId=1"
   ```
5. **2020-06-16 21:00**
   ```sh
   curl "http://localhost:8080/prices?date=2020-06-16T21:00:00&productId=35455&brandId=1"
   ```

## How to Build and Run

1. Build the complete project:
   ```sh
   mvn clean install
   ```
2. Start the application from the `prices-boot` module:
   ```sh
   cd prices-boot
   mvn spring-boot:run
   ```

## Best Practices Applied

- Hexagonal architecture (ports and adapters).
- Clear separation of responsibilities by module.
- Centralized DTOs and mappers in `prices-shared`.
- Decoupled startup in `prices-boot`.
- Component, entity, and repository scanning configured.
- Recommended integration tests.
- Clean, professional, and scalable code.

## Contact

For any technical questions, contact the responsible developer.
