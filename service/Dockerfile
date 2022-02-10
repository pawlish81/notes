FROM openjdk:11

ARG WAR_FILE=target/*.jar

COPY $WAR_FILE /app.jar

ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
