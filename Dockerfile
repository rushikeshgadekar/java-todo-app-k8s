# Multi-stage build for a Spring Boot executable jar (Maven)

# 1) Build stage
FROM maven:3.9.7-eclipse-temurin-21 AS build

WORKDIR /workspace

# Cache Maven dependencies and build artifacts
COPY pom.xml .
COPY src src

# Build the executable jar
RUN mvn -B -DskipTests package


# 2) Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /workspace/target/*.jar app.jar

# Optional: expose the configured Spring Boot port
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
