
# Use an Open JDK base image
FROM openjdk:17

VOLUME /tmp

# Set the working directory in the container 
COPY target/auth_service.jar auth_service.jar

# Command to run the Spring boot application  
ENTRYPOINT [ "java", "-jar", "/auth_service.jar" ]


# Expose the port that the spring boot app will listen to 
EXPOSE 8081

