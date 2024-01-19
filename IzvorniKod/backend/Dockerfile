FROM maven:3.6.3-openjdk-17 AS builder
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=builder /target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]