FROM maven:3.6.3-openjdk-17 AS builder
COPY . .
RUN mvn clean package -DskipTests

# Use the official PostgreSQL image as the base image
FROM postgres:alpine
ENV POSTGRES_DB demodb
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
EXPOSE 5432
VOLUME /var/lib/postgresql/data



FROM openjdk:17-alpine
COPY --from=builder /target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]