FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package


EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
 
