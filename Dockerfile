#FROM maven:latest
#WORKDIR /app
#COPY . .
#RUN mvn package
#EXPOSE 8080
#CMD ["java", "-jar", "target/JWT-validator-0.0.1-SNAPSHOT.jar"]

FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final com JDK 21 para execução
FROM eclipse-temurin:21-jre

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado da etapa de build
# Usa find para pegar dinamicamente o JAR gerado com a versão do pom.xml
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta (altere conforme seu `application.properties`)
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.jar"]