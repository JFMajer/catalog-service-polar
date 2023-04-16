FROM openjdk:17-jdk-alpine

# Create new group and user
RUN addgroup -S spring && adduser -S spring -G spring --disabled-password
USER spring:spring

# Set the working directory to /app
WORKDIR /app

# Set JAR file
ARG JAR_FILE=build/libs/*.jar

# Copy jar to /app
COPY ${JAR_FILE} app.jar

EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]