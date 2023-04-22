FROM openjdk:11-jdk-slim

# set the working directory
WORKDIR /app

# copy the jar file to the container
COPY build/libs/com.example.borutoserver-all.jar app.jar

# expose port 8080
EXPOSE 8080

# set the command to run the jar file
CMD ["java", "-jar", "app.jar"]