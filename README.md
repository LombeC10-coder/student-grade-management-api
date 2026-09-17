# Student Grade Management API

A Java 17 and Spring Boot REST service for managing students, modules, registrations and grades. The application models related academic records, applies validation and business rules, persists data through JPA, and returns structured responses when operations cannot be completed.

## Engineering Focus

- Layered Java design separating HTTP handling, domain models and persistence
- RESTful JSON services built with Spring Boot
- Relational data modelling with JPA and H2
- Input validation and predictable exception handling
- Unit and controller testing with JUnit
- API documentation with OpenAPI
- Maven-based build and dependency management
- Automated quality checks with JaCoCo, Checkstyle and SpotBugs
- Continuous integration through GitHub Actions

## Domain Model

The service represents four connected areas:

- **Students** - learners whose registrations and grades are managed
- **Modules** - units of study available to students
- **Registrations** - relationships between students and modules
- **Grades** - results attached to valid registrations

This structure keeps data relationships explicit and allows invalid or missing operations to be handled consistently.

## Technology

- Java 17
- Spring Boot 3
- Maven
- Spring Data JPA
- H2 Database
- REST and JSON
- OpenAPI / Swagger UI
- JUnit
- JaCoCo
- Checkstyle
- SpotBugs
- GitHub Actions

## Quality and Testing

The Maven build is configured to run automated tests and generate a JaCoCo coverage report. A package-level line-coverage threshold of 90% is configured, excluding the Spring Boot application entry point. Checkstyle and SpotBugs provide additional static-analysis feedback.

Every push and pull request to `main` triggers the GitHub Actions workflow, which:

1. builds and verifies the project with Java 17;
2. runs the automated test suite;
3. applies the configured coverage rule;
4. runs Checkstyle and SpotBugs; and
5. uploads the JaCoCo report as a workflow artifact when available.

## Run Locally

### Prerequisites

- Java 17

The repository includes the Maven Wrapper, so a separate Maven installation is not required.

### Start the application

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
mvnw.cmd spring-boot:run
```

### Run tests and quality checks

```bash
./mvnw verify
./mvnw checkstyle:check spotbugs:check
```

### View API documentation

With the application running, open:

```text
http://localhost:8080/swagger-ui/index.html
```

## Project Background

The service began as university software-engineering coursework and has been retained and improved as a portfolio example of Java backend development. It demonstrates how I translate requirements into a domain model, organise responsibilities across application layers, validate relational data and use automated feedback to improve reliability.

## Future Improvements

- Add PostgreSQL as a production-style database profile
- Expand integration tests around database behaviour
- Add pagination and filtering to collection endpoints
- Containerise the application and database
- Publish example requests and responses
