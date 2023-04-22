FROM openjdk:11-jre-slim

ENV APP_HOME /app
WORKDIR $APP_HOME

COPY build/libs/fat.jar $APP_HOME/fat.jar

CMD ["java", "-jar", "fat.jar"]
