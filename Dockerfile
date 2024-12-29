# Use an OpenJDK base image
FROM openjdk:23-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/StudentIS-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
