FROM maven:3.9-amazoncorretto-21-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.22

ENV DB_URL="jdbc:postgresql://db:5432/conta"

COPY --from=build app/target/bank-api-1.0.jar bank-api.jar

EXPOSE 5432
EXPOSE 8080

CMD ["java", "-jar", "bank-api.jar"]