FROM maven:3.9-amazoncorretto-21-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.22

ENV db_url="jdbc:postgresql://host.docker.internal:5432/conta"
ENV password="vinicius"
ENV user="postgres"

COPY --from=build app/target/Conta-1.0.jar app.jar

EXPOSE 5432
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]