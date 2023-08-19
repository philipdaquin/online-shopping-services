
# Use an open JDK base image
FROM openjdk:17

VOLUME /tmp

COPY target/config_server.jar config_server.jar

# Command to run the Spring boot application  
ENTRYPOINT [ "java", "-jar", "/config_server.jar" ]


# Expose the port that the spring boot app will listen to 
EXPOSE 9296
