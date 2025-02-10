# Use a base image with Java runtime
FROM openjdk:21-jdk-slim 

# Create a non-root user and set its home directory
RUN adduser --disabled-password --gecos "" springuser

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the target folder to the container
COPY target/tictactoe-app.war /app/tictactoe.war

# Set the non-root user as the one to run the application
USER springuser

# Expose the port the application will run on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "tictactoe.war"]
