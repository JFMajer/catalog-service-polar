FROM amazoncorretto:17.0.6-al2023 AS builder

# Set the working directory to /app
WORKDIR /app

# Set jar file name
ARG JAR_FILE=build/libs/catalog-service-0.0.1-SNAPSHOT.jar

# Copy jar-file to /app with proper permissions
COPY ${JAR_FILE} app.jar

RUN java -Djarmode=layertools -jar app.jar extract


FROM amazoncorretto:17.0.6-al2023

WORKDIR /app

# Copy the extracted jar file from the builder image
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]