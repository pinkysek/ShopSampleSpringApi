
# ShopSampleSpringApi

ShopSampleSpringApi is a sample implementation of a basic **REST API** using **Java, Spring Boot, Hibernate, and PostgreSQL**. The project demonstrates how to create endpoints for managing products and includes support for pagination and partial updates. The application follows a **layered architecture** with unit tests for each layer.

## Features

- **Product Management**: A simple REST API for handling products via `ProductController`.
- **Endpoints**:
  - `GET /api/products/{id}` - Retrieve a product by ID.
  - `GET /api/products/` - Retrieve all products.
  - `GET /api/products/paging?pageNumber=1&pageSize=10` - Retrieve products with pagination support.
  - `PATCH /api/products/{id}/description` - Update a product's description.
- **API Documentation**: Swagger documentation available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Prerequisites

- **Development Environment**:
  - [IntelliJ IDEA](https://www.jetbrains.com/idea/) or any other Java IDE.
  - Java 17 or higher.
- **Database**:
  - [PostgreSQL](https://www.postgresql.org/) server for production use.
- **Build Tool**:
  - [Maven](https://maven.apache.org/) for dependency management and building the project.

## Configuration

- **Production Settings**: Configure database connection in `src/main/resources/application.properties`.
- **Test Settings**: Test-specific database configuration in `src/test/resources/application-test.properties`.

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/pinkysek/ShopSampleSpringApi.git
   cd ShopSampleSpringApi
   ```

2. **Set up the PostgreSQL database**:
   - Create a new database in PostgreSQL.
   - Update the connection string and credentials in `src/main/resources/application.properties`.

3. **Build and run the application**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the API documentation**:
   - Open your browser and go to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Tests

1. **Run Unit and Integration Tests**:
   ```bash
   mvn test
   ```

2. **Test Configuration**:
   - The tests use the configuration in `src/test/resources/application-test.properties`.

## License

This project is open-source and available under the [MIT License](LICENSE).
