
# Use an open JDK base image
FROM openjdk:17

VOLUME /tmp

COPY target/product_service.jar product_service.jar

# Command to run the Spring boot application  
ENTRYPOINT [ "java", "-jar", "/product_service.jar" ]


# Expose the port that the spring boot app will listen to 
EXPOSE 8082
