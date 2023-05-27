FROM eclipse-temurin:17-jdk-alpine
WORKDIR /opt/app
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} properties-ms.jar
ENTRYPOINT ["java","-jar","properties-ms.jar"]