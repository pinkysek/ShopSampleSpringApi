# ShopSampleSpringApi

ShopSampleSpringApi is a sample implementation of a basic **REST API** using **Java, Spring Boot, Hibernate, and PostgreSQL**. The project demonstrates how to create endpoints for managing products and includes support for pagination, partial updates, and JWT-based authentication. The application follows a **layered architecture** with unit tests for each layer.

## Features

- **Product Management**: A simple REST API for handling products via `ProductController`.
- **JWT Authentication**: Stateless authentication using signed JWT tokens (HMAC-SHA).
- **Role-Based Access Control**: Endpoints are protected based on user roles (`ROLE_USER`, `ROLE_ADMIN`).
- **Input Validation**: Request bodies are validated using Bean Validation annotations.
- **API Documentation**: Swagger UI with Bearer token support.

## Endpoints

### Authentication (public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Login and receive a JWT token |

### Products

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `GET` | `/api/products/{id}` | Public | Retrieve a product by ID |
| `GET` | `/api/products/` | Public | Retrieve all products |
| `GET` | `/api/products/paging?pageNumber=1&pageSize=10` | Public | Retrieve products with pagination |
| `POST` | `/api/products/` | Admin | Create a new product |
| `PUT` | `/api/products/{id}` | Admin | Update a product |
| `DELETE` | `/api/products/{id}` | Admin | Delete a product |
| `PATCH` | `/api/products/{id}/description` | Authenticated | Update a product's description |

## Prerequisites

- **Development Environment**:
  - [IntelliJ IDEA](https://www.jetbrains.com/idea/) or any other Java IDE.
  - Java 25 or higher.
- **Database**:
  - [PostgreSQL](https://www.postgresql.org/) server for local/production use, or use Docker Compose (see below).
- **Build Tool**:
  - [Maven](https://maven.apache.org/) for dependency management and building the project.
- **Docker** _(optional)_:
  - [Docker](https://www.docker.com/) with Docker Compose for containerized setup.

## Configuration

The following environment variables must be set before running the application:

| Variable | Required | Description |
|----------|----------|-------------|
| `APP_JWT_SECRET` | Yes | JWT signing secret (min. 64 characters for HS512) |
| `APP_JWT_EXPIRATION_MS` | No | Token expiration in ms (default: `3600000` = 1 hour) |
| `APP_CORS_ALLOWED_ORIGINS` | No | Allowed CORS origins (default: `http://localhost:3000`) |
| `DB_USERNAME` | Yes (local/Docker) | PostgreSQL username |
| `DB_PASSWORD` | Yes (local/Docker) | PostgreSQL password |
| `DB_URL` | Yes (prod) | Full JDBC URL for production database |

- **Production Settings**: Configure database connection and environment variables in `src/main/resources/application-prod.properties`.
- **Test Settings**: Test-specific database configuration in `src/test/resources/application-test.properties`.

## Getting Started

### Option A — Docker Compose (recommended)

No local PostgreSQL installation needed. Docker handles everything.

1. **Clone the repository**:
   ```bash
   git clone https://github.com/pinkysek/ShopSampleSpringApi.git
   cd ShopSampleSpringApi
   ```

2. **Create a `.env` file** in the project root (use `.env` as a template):
   ```env
   DB_USERNAME=shopsample
   DB_PASSWORD=shopsample123
   APP_JWT_SECRET=your-very-long-secret-key-at-least-64-characters-long-for-hs512
   ```

3. **Build and start all services**:
   ```bash
   docker compose up --build
   ```
   PostgreSQL starts first; the application waits until the database is healthy before connecting.

4. **Stop the application**:
   ```bash
   docker compose down        # stop and remove containers
   docker compose down -v     # also remove database volume (wipes data)
   ```

### Option B — Local Maven

Requires a running PostgreSQL instance.

1. **Clone the repository**:
   ```bash
   git clone https://github.com/pinkysek/ShopSampleSpringApi.git
   cd ShopSampleSpringApi
   ```

2. **Set required environment variables**:
   ```bash
   export DB_USERNAME=shopsample
   export DB_PASSWORD=shopsample123
   export APP_JWT_SECRET=your-very-long-secret-key-at-least-64-characters-long-for-hs512
   ```

3. **Build and run the application**:
   ```bash
   mvn clean install
   mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
   ```

### Accessing the API

- Open your browser and go to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
- Use the **Authorize** button to enter your JWT token (obtain it via `POST /api/auth/login`).

## Authentication Flow

1. Register a user via `POST /api/auth/register`
2. Login via `POST /api/auth/login` — the response contains a `token`
3. Include the token in subsequent requests as a Bearer header:
   ```
   Authorization: Bearer <your-token>
   ```

## Tests

1. **Run Unit and Integration Tests**:
   ```bash
   mvn test
   ```

2. **Test Configuration**:
   - The tests use the configuration in `src/test/resources/application-test.properties`.

## License

This project is open-source and available under the [MIT License](LICENSE).
