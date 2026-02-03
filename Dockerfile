# syntax=docker/dockerfile:1
# Multi-stage Dockerfile for Spring Boot
# Stage 1: Builder (Maven)
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copy Maven config first for better layer caching
COPY pom.xml .
COPY src ./src

# Build the app (skip tests for faster builds; add -DskipTests if needed)
RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests

# Stage 2: Runtime (JRE only, smaller image)
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Copy the JAR from builder
COPY --from=builder /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
