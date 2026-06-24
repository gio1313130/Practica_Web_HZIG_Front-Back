FROM eclipse-temurin:25-jdk-alpine
LABEL authors="yo"
ARG JAR_FILE=target/demo1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_miniinventario.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app_miniinventario.jar"]