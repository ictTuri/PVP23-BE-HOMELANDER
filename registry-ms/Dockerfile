FROM eclipse-temurin:17-jdk-alpine
WORKDIR /opt/app
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} registry-ms.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","registry-ms.jar"]