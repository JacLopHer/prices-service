# Prices Service - Hexagonal Architecture

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://shields.io/) [![Java Version](https://img.shields.io/badge/java-17%2B-blue)](https://www.oracle.com/java/) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Overview
This project is a technical test for price management, implemented with professional hexagonal architecture using Spring Boot and Maven multi-module. It allows querying product prices based on date, product, and brand.

## Purpose
Showcase best practices in design, architecture, and development in Java, applying SOLID principles and hexagonal architecture patterns.

## Technologies Used
Below are the technologies used and their purpose in the project:
- **Java 17+**: Main language, leveraging the latest features and performance improvements.
- **Spring Boot**: Framework for building robust and scalable enterprise applications, simplifying configuration and deployment.
- **Maven multi-module**: Enables separation of responsibilities and scalable project structure.
- **JPA/Hibernate**: Abstraction for data access, facilitating persistence and object-relational mapping.
- **H2 Database**: In-memory database for fast development and testing.
- **REST API**: Service exposure via HTTP following best design practices.
- **JUnit & Mockito**: Unit and integration testing, ensuring quality and maintainability.

## Prerequisites
- Java 17 or higher
- Maven 3.8+
- Git

## Installation & Running
1. Clone the repository:
   ```sh
   git clone <repository-url>
   ```
2. Build the project:
   ```sh
   ./mvnw clean install
   ```
3. Run the application:
   ```sh
   ./mvnw spring-boot:run -pl prices-boot
   ```
4. Access the main endpoint at:
   `http://localhost:8080/prices`

## Local Configuration
- The project uses Spring Boot profiles (`application-dev.yml`, `application-prod.yml`, etc.) in `prices-boot/src/main/resources`.
- No additional environment variables are required for local execution.
- The H2 database is automatically initialized with sample data.

## Running Tests
To run tests:
```sh
./mvnw test
```
Test reports are located in the `target/surefire-reports/` folder of each module.

## Module Structure
The project is divided into 6 modules, each with a clear responsibility:
- **prices-domain**: Core domain. Business entities and ports (interfaces). No external dependencies.
- **prices-application**: Services and use cases. Orchestrates business logic using domain ports.
- **prices-infrastructure**: Persistence adapters (JPA/H2), data initialization, and configuration.
- **prices-api**: REST controllers and endpoint configuration.
- **prices-shared**: DTOs, mappers, utilities, and shared exceptions.
- **prices-boot**: Main class and global configuration.

## Folder Structure
```
prices-service/
├── prices-domain/
├── prices-application/
├── prices-infrastructure/
├── prices-api/
├── prices-shared/
├── prices-boot/
```

## Main Endpoint
- **GET /prices**
    - Parameters: `date` (ISO-8601 with timezone), `productId`, `brandId`
    - Example:
      ```sh
      curl "http://localhost:8080/prices?date=2020-06-14T10:00:00+02:00&productId=35455&brandId=1"
      ```
    - Example response:
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

### Example Cases & Expected Responses
| Query Date                 | productId | brandId | priceList | startDate                | endDate                  | price  | currency |
|----------------------------|-----------|---------|-----------|--------------------------|--------------------------|--------|----------|
| 2020-06-14T10:00:00+02:00  | 35455     | 1       | 1         | 2020-06-14T00:00:00+02:00| 2020-12-31T23:59:59+02:00| 35.50  | EUR      |
| 2020-06-14T16:00:00+02:00  | 35455     | 1       | 2         | 2020-06-14T15:00:00+02:00| 2020-06-14T18:30:00+02:00| 25.45  | EUR      |
| 2020-06-14T21:00:00+02:00  | 35455     | 1       | 1         | 2020-06-14T00:00:00+02:00| 2020-12-31T23:59:59+02:00| 35.50  | EUR      |
| 2020-06-15T10:00:00+02:00  | 35455     | 1       | 3         | 2020-06-15T00:00:00+02:00| 2020-06-15T11:00:00+02:00| 30.50  | EUR      |

## Endpoint Documentation
- **GET /prices**: Query prices by date, product, and brand.
- **GET /actuator/health**: Application health status (Spring Boot Actuator).

## Error Examples
- If no price is found:
  ```json
  {
    "error": "No price found for the given parameters"
  }
  ```
- If parameters are invalid:
  ```json
  {
    "error": "Invalid parameters"
  }
  ```

## Frequently Asked Questions (FAQ)
- **Can I change the database?** Yes, by modifying the configuration in `prices-boot/src/main/resources/application.yml`.
- **How do I add new endpoints?** Add controllers in the `prices-api` module and use cases in `prices-application`.

## Efficiency and Scalability

**Composite indexes:** The `prices` table includes a composite index on the fields `product_id`, `brand_id`, `start_date`, `end_date`, `priority DESC` to optimize the main query. Check its usage with `EXPLAIN` in your database.

**Connection pool:** Configured in `application.yml` to support high concurrency. Adjust the values according to your environment and expected load.

**Read cache:** If prices change infrequently and access is very frequent, consider integrating Redis and using `@Cacheable` in the service to reduce database load.

**Monitoring:** Use Spring Actuator, Prometheus, and Grafana to monitor performance and adjust parameters as the table grows and usage patterns change.

**Filtering logic:** Keep selection logic in SQL, not in Java, to avoid unnecessary in-memory calculations and improve efficiency.

## References & Resources
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Maven Documentation](https://maven.apache.org/)
- [JPA/Hibernate](https://hibernate.org/)
- [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/)

## Contributing
Contributions are welcome. Please open an issue or submit a pull request. Follow the style guide and run tests before submitting your PR.

## Contact & Support
Author: Jacinto López Hernández
Email: jacilopezwork@gmail.com
Support channel: GitHub Issues

## License
This project is distributed under the MIT license. See the LICENSE file for details.
