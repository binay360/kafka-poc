# Use Java 21
FROM eclipse-temurin:21-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the jar
COPY target/likes-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
