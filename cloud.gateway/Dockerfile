FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/cloud.gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} .
CMD [ "java", "-jar",  "/cloud.gateway-0.0.1-SNAPSHOT.jar"]