# syntax=docker/dockerfile:1

FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY src ./src

CMD ["./mvnw", "spring-boot:run", "-Dmaven.test.skip=true"]