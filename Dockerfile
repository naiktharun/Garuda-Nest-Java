# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy your application JAR file into the container
COPY target/WaterSharing-1.0.0.jar app.jar

# Expose the port (optional, for documentation purposes)
EXPOSE 8080

# Command to run your application, ensuring it reads the PORT environment variable
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
