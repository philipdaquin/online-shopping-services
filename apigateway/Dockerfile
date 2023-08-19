
# Use an Open JDK base image
FROM openjdk:17

VOLUME /tmp

# Set the working directory in the container 
COPY target/apigateway.jar apigateway.jar

# Command to run the Spring boot application  
ENTRYPOINT [ "java", "-jar", "/apigateway.jar" ]


# Expose the port that the spring boot app will listen to 
EXPOSE 9090

