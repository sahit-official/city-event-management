FROM openjdk:17-jdk-slim AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn clean package


EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
 
