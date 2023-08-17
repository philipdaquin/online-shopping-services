FROM openjdk:17

ARG JAR_FILE = target/*.jar

COPY ${JAR_FILE} auth_service.jar

ENTRYPOINT [ "java", "-jar", "/auth_service.jar" ]

EXPOSE 8081