
# Use an open JDK base image
FROM openjdk:17

VOLUME /tmp

COPY target/config_server.jar config_server.jar

# Command to run the Spring boot application  
ENTRYPOINT [ "java", "-jar", "/config_server.jar" ]


# Expose the port that the spring boot app will listen to 
EXPOSE 9296

# https://github.com/akolata/spring-boot-actuator-docker-health-check/blob/master/config-server/Dockerfile
# FROM openjdk:17-slim as builder
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} application.jar
# RUN java -Djarmode=layertools -jar application.jar extract

# FROM eclipse-temurin:17-jre-alpine

# ENV JDK_JAVA_OPTIONS="-Xms256m -Xmx512m"

# RUN apk --no-cache add bash curl

# RUN mkdir /app
# RUN addgroup -S spring && adduser -S spring -G spring
# WORKDIR /app

# COPY --from=builder dependencies/ /app
# COPY --from=builder spring-boot-loader/ /app
# COPY --from=builder snapshot-dependencies/ /app
# COPY --from=builder application/ /app

# RUN chown -R spring:spring .
# USER spring:spring

# ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-cp", "BOOT-INF/classes:BOOT-INF/lib/*", "pl.akolata.healthcheck.demo.configserver.ConfigServerApplication"]