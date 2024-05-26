# User CRUD API

A RESTful web service for managing user data. It allows you to perform CRUD (Create, Read, Update, Delete) operations on user records. This README provides an overview of the project, its features, how to set it up, and how to use it.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Configuration](#configuration)
- [Usage](#usage)
- [Documentation](#documentation)
    - [API Documentation](#api-documentation)
    - [JavaDocs](#java-documentation)
- [Testing](#testing)
- [License](#license)

## Features

- CRUD operations on user entities (Create, Read, Update, Delete).
- Validation of user data inputs.
- Exception handling for error scenarios.
- Integration with an H2 in-memory database for data persistence.
- API documentation using Swagger UI.

## Technologies Used

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Swagger UI](https://swagger.io/tools/swagger-ui)
- [JUnit 5](https://junit.org/junit5)
- [Mockito](https://site.mockito.org)
- [Maven](https://maven.apache.org)

## Getting Started

### Prerequisites

Before setting up the project, ensure you have the following installed:

- Java Development Kit (JDK) 8 or later
- Maven

### Installation

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/kxmohelo/user-crud-api.git
    ```

2. Navigate to the project directory:

    ```bash
    cd user-crud-api
    ```

### Configuration

1. Configure the application properties:
    <br/><br/>
   - Copy the application.properties.example file to application.properties.
   - Update the database URL, username, and password in application.properties as per your environment.
    <br/><br/>
2. (Optional) Configure test properties:
   <br/><br/>
   - Copy the application-test.properties.example file to application-test.properties.
   - Update the database URL, username, and password in application-test.properties if necessary.

## Usage

To run the application, execute the following command in the project directory:

```bash
mvn spring-boot:run
```

Once the application is running, you can access the API endpoints using tools like Postman or curl.

## Documentation

### API Documentation

The API documentation is available using Swagger UI. Once the application is running, navigate to
http://localhost:8080/swagger-ui.html in your web browser to access the API documentation.

Additionally, you can:

- Navigate to http://localhost:8080/api-docs to view the documentation in `json` format.
- Navigate to http://localhost:8080/api-docs.yaml to view the documentation in `yaml` format.

### Java Documentation

You can access the java documentation that references the classes, methods, and other components of this application 
[here](https://kxmohelo.github.io/user-crud-api/).

Alternatively, you can access the static java documentation files [here](/docs).

## Testing

To run the tests for the application, execute the following command:

```bash
mvn test
```

This will run the unit tests and integration tests for the application.

## License

This project is licensed under the MIT License.