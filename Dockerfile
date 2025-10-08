FROM openjdk:21 AS build
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

## Etapa 1: Compilação com Maven
#FROM maven:3.9.6-eclipse-temurin-21 AS build
#WORKDIR /app
#
## Copia arquivos necessários
#COPY pom.xml .
#COPY src ./src
#
## Compila a aplicação (gera o JAR)
#RUN mvn clean package -DskipTests
#
## Etapa 2: Execução com JDK 21
#FROM openjdk:21
#WORKDIR /app
#
## Copia o JAR gerado na etapa anterior
#COPY --from=build /app/target/*.jar app.jar
#
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]