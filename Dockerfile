#FROM openjdk:21
#WORKDIR /app
#COPY target/JWT-validator-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]


FROM maven:latest
WORKDIR /app
COPY . .
RUN mvn package
EXPOSE 8080
CMD ["java", "-jar", "target/JWT-validator-0.0.1-SNAPSHOT.jar"]