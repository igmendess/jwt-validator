#FROM maven:latest
#WORKDIR /app
#COPY . .
#RUN mvn package
#EXPOSE 8080
#CMD ["java", "-jar", "target/JWT-validator-0.0.1-SNAPSHOT.jar"]

# Etapa 1: build do JAR usando Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Etapa 2: imagem de runtime com o JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR com base na versão do pom.xml (usando um argumento)
ARG JAR_VERSION
COPY --from=build /app/target/*-${JAR_VERSION}.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]