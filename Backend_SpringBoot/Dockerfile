FROM maven:3.9-eclipse-temurin-25-alpine AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY --from=build /app/target/demo1-0.0.1-SNAPSHOT.jar app_miniinventario.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app_miniinventario.jar"]