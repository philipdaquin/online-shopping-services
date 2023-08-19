
# Use an open JDK base image
FROM openjdk:17

VOLUME /tmp

COPY target/shopping_cart.jar shopping_cart.jar

# Command to run the Spring boot application  
ENTRYPOINT [ "java", "-jar", "/shopping_cart.jar" ]

# Expose the port that the spring boot app will listen to 
EXPOSE 8084
