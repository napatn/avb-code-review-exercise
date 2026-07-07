# Mobile Auth Service

A Spring Boot-based authentication and authorization service for Ascend Virtual Bank's mobile applications. Provides JWT-based authentication, token validation, and session management for secure user access across mobile platforms.

## Quick Start

### Prerequisites
- Java 11+
- Maven 3.6+

### Build & Run

```bash
# Navigate to project
cd /Users/napat.nat/Stuffs/Projects/avb-code-review-exercise/

# Build the project
mvn clean compile

# Run with Spring Boot Maven plugin
mvn spring-boot:run

# Application starts on http://localhost:8080/api/v1
```

### Testing Endpoints

#### 1. Health Check
```bash
curl http://localhost:8080/api/v1/auth/health
# Response: OK
```

#### 2. Login
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'

# Response: {"accessToken":"eyJhbGci...","tokenType":"Bearer"}
```

Test Credentials:
- Username: `testuser` / Password: `testpass`
- Username: `admin` / Password: `admin123`

#### 3. Validate Token
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}' | jq -r .accessToken)

curl -X POST http://localhost:8080/api/v1/auth/validate \
  -H "Content-Type: application/json" \
  -d "{\"token\":\"$TOKEN\"}"

# Response: true
```

#### 4. Get User Info
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}' | jq -r .accessToken)

curl -H "Authorization: Bearer $TOKEN" \
  http://localhost:8080/api/v1/auth/user

# Response: User: testuser
```

#### 5. Refresh Token
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}' | jq -r .accessToken)

curl -X POST http://localhost:8080/api/v1/auth/refresh \
  -H "Authorization: Bearer $TOKEN"

# Response: {"accessToken":"eyJhbGci...","tokenType":"Bearer"}
```

---

## Purpose

The Mobile Auth Service provides:

- **User Authentication** - Secure login via username/password with JWT token generation
- **Token Validation** - Verify token authenticity and expiration for incoming requests
- **Session Management** - Maintain user session state with refresh token support
- **User Information** - Retrieve authenticated user details for downstream services
- **Health Monitoring** - Service health checks for load balancers and orchestrators

## API Endpoints

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/auth/login` | None | Authenticate user, return JWT token |
| POST | `/auth/refresh` | Header | Refresh expired token |
| POST | `/auth/validate` | Body | Validate token validity |
| GET | `/auth/user` | Header | Get authenticated user info |
| GET | `/auth/health` | None | Health check |

---

## Architecture

The service follows a layered architecture:

- **Controller Layer** (`AuthController`) - HTTP request handling and response mapping
- **Service Layer** (`AuthService`) - Business logic for authentication and token management
- **Security Layer** (`JwtTokenProvider`, `SecurityConfig`) - JWT token generation/validation and Spring Security configuration
- **Repository Layer** (`UserRepository`) - User data access and persistence
- **Models** (`User`) - Domain entities representing authenticated users

## Technology Stack

- **Framework**: Spring Boot 3.1.5
- **Language**: Java 11
- **Auth**: JWT (JJWT 0.11.2)
- **Security**: Spring Security 6.1.5
- **Build**: Maven 3.9+
- **Server**: Tomcat (embedded)

---

## Technology Stack

- **Framework**: Spring Boot 3.1.5
- **Language**: Java 11
- **Auth**: JWT (JJWT 0.11.2)
- **Security**: Spring Security 6.1.5
- **Build**: Maven 3.9+
- **Server**: Tomcat (embedded)

---

## Getting Started

### Prerequisites
- Java 11+
- Maven 3.6+

### Build & Run

```bash
# Navigate to project
cd /Users/napat.nat/Stuffs/Projects/avb-code-review-exercise/

# Build the project
mvn clean compile

# Run with Spring Boot Maven plugin
mvn spring-boot:run

# Application starts on http://localhost:8080/api/v1
```

### Configuration

The service is configured via `src/main/resources/application.yml`. Key settings:

```yaml
server:
  port: 8080

spring:
  application:
    name: mobile-auth-service
  security:
    jwt:
      secret: ${JWT_SECRET}
      expiration: 3600000  # 1 hour in milliseconds
```

To run on a different port:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"
```

---

## Testing

### Example Usage

#### 1. Login
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'

# Response: {"accessToken":"eyJhbGci...","tokenType":"Bearer"}
```

#### 2. Validate Token
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}' | jq -r .accessToken)

curl -X POST http://localhost:8080/api/v1/auth/validate \
  -H "Content-Type: application/json" \
  -d "{\"token\":\"$TOKEN\"}"

# Response: true
```

#### 3. Get User Info
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}' | jq -r .accessToken)

curl -H "Authorization: Bearer $TOKEN" \
  http://localhost:8080/api/v1/auth/user

# Response: User: testuser
```

#### 4. Health Check
```bash
curl http://localhost:8080/api/v1/auth/health

# Response: OK
```

---

## Project Structure

```
src/main/java/com/avb/mobileauth/
├── controller/AuthController.java          HTTP request handling
├── service/AuthService.java                Authentication business logic
├── security/
│   ├── JwtTokenProvider.java               JWT generation and validation
│   └── SecurityConfig.java                 Spring Security configuration
├── model/User.java                         User domain entity
├── dto/
│   ├── LoginRequest.java                   Login request DTO
│   ├── TokenResponse.java                  Token response DTO
│   └── TokenValidationRequest.java         Token validation request DTO
└── repository/UserRepository.java          User data access

src/main/resources/
└── application.yml                         Application configuration
```

---

## Environment Variables

Set these environment variables when deploying:

| Variable | Description | Default |
|----------|-------------|---------|
| `JWT_SECRET` | Secret key for signing JWT tokens | (required) |
| `JWT_EXPIRATION` | Token expiration time in milliseconds | 3600000 |
| `SERVER_PORT` | HTTP server port | 8080 |

---

**Version**: 2.0  
**Status**: Production-ready  
**Last Updated**: July 7, 2026
