# Stage 1
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


#Stage 2
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/java-spring-prfct-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

LABEL authors="sxlrd3000"

ENTRYPOINT ["java", "-jar", "app.jar"]