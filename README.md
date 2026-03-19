# Todo Service 📝

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.7-red.svg)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)
[![Kubernetes](https://img.shields.io/badge/Kubernetes-Ready-326ce5.svg)](https://kubernetes.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A modern, production-ready Todo application built with Spring Boot 3, featuring REST APIs, web UI, and Kubernetes deployment.

![Demo Animation](https://via.placeholder.com/800x400/00ff00/000000?text=Demo+Animation+Coming+Soon)

## ✨ Features

- **RESTful API**: Complete CRUD operations for todos
- **Web UI**: Interactive web interface with Thymeleaf templates
- **Database**: H2 in-memory database (configurable)
- **Validation**: Input validation with Bean Validation
- **Documentation**: OpenAPI/Swagger integration
- **Monitoring**: Spring Boot Actuator endpoints
- **Containerization**: Docker support
- **Orchestration**: Kubernetes manifests included
- **Logging**: Configurable logging with patterns

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web UI        │    │   REST API      │    │   Database      │
│   (Thymeleaf)   │◄──►│   (Controllers) │◄──►│   (H2/JPA)     │
│                 │    │                 │    │                 │
│ • index.html    │    │ • /todo/create  │    │ • todos table   │
│ • todos.html    │    │ • /todo/todos   │    │                 │
│ • create-todo.html │ │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🚀 Quick Start

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- Docker (optional)
- Kubernetes cluster (optional)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/rushikeshgadekar/java-todo-app-k8s.git
   cd todo-service
   ```

2. **Build the application**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Web UI: http://localhost:8081/web/
   - API Docs: http://localhost:8081/swagger-ui/index.html
   - H2 Console: http://localhost:8081/h2/console (if enabled)

### Docker Deployment

1. **Build Docker image**
   ```bash
   docker build -t todo-service:latest .
   ```

2. **Run with Docker**
   ```bash
   docker run -p 8081:8081 todo-service:latest
   ```

### Kubernetes Deployment

1. **Apply Kubernetes manifests**
   ```bash
   kubectl apply -f k8s/
   ```

2. **Access via Ingress**
   - Add to `/etc/hosts`: `127.0.0.1 todo-service.local`
   - Visit: http://todo-service.local

## 📚 API Documentation

### Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/todo/create` | Create a new todo |
| GET | `/todo/todos` | Get all todos |

### Example API Usage

**Create Todo:**
```bash
curl -X POST http://localhost:8081/todo/create \
  -H "Content-Type: application/json" \
  -d '{
    "todo": "Learn Spring Boot",
    "description": "Complete the Spring Boot tutorial"
  }'
```

**Get All Todos:**
```bash
curl http://localhost:8081/todo/todos
```

### Data Model

```json
{
  "id": "uuid-string",
  "todo": "Task title (3-255 chars)",
  "description": "Optional description (max 1000 chars)"
}
```

## 🎨 Web Interface

The application provides a responsive web interface with the following pages:

- **Home Page** (`/`): Welcome page with navigation
- **Todos List** (`/web/todos`): View all todos with delete functionality
- **Create Todo** (`/web/create`): Form to add new todos

### Screenshots

*Add screenshots here*

## ⚙️ Configuration

### Application Properties

Key configuration options in `application.yml`:

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:h2:mem:testdb
  jpa:
    hibernate:
      ddl-auto: update

management:
  endpoints:
    web:
      exposure:
        include: "health,metrics,info"
```

### Environment Variables

- `SPRING_PROFILES_ACTIVE`: Set active profile (default, prod, etc.)
- `SERVER_PORT`: Override server port

## 🧪 Testing

Run tests with Maven:
```bash
mvn test
```

## 📊 Monitoring

### Actuator Endpoints

- Health: `/actuator/health`
- Metrics: `/actuator/metrics`
- Info: `/actuator/info`

## 🐳 Docker

### Build Multi-stage Image

```dockerfile
# Build stage
FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src src
RUN mvn -B -DskipTests package

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
```

## ☸️ Kubernetes

### Deployment Components

- **Deployment**: 3 replicas with readiness probes
- **Service**: ClusterIP service on port 8081
- **Ingress**: Nginx ingress for external access

### Scaling

```bash
kubectl scale deployment todo-service --replicas=5
```

## 🔧 Development

### Project Structure

```
src/
├── main/
│   ├── java/com/productivity/todoapp/todoservice/
│   │   ├── TodoApplication.java
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── mapper/
│   │   ├── repository/
│   │   ├── response/
│   │   └── service/
│   └── resources/
│       ├── application.yml
│       └── templates/
└── test/
    └── java/com/productivity/todoapp/todoservice/
```

### Technologies Used

- **Framework**: Spring Boot 3.4.0
- **Language**: Java 21
- **Build Tool**: Maven 3.9.7
- **Database**: H2 (in-memory)
- **ORM**: Spring Data JPA + Hibernate
- **Validation**: Bean Validation
- **Mapping**: MapStruct
- **Documentation**: SpringDoc OpenAPI
- **Web**: Thymeleaf
- **Container**: Docker
- **Orchestration**: Kubernetes

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- H2 Database for lightweight development
- Kubernetes community for orchestration tools

---

*Made with ❤️ using Spring Boot*