FROM maven:latest
WORKDIR /app
COPY . .
RUN mvn package
EXPOSE 8080
CMD ["java", "-jar", "target/JWT-validator-0.0.1-SNAPSHOT.jar"]