# Stage 1: Build the JAR inside a Java 8 container
FROM maven:3.6.3-jdk-8-slim AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Stage 2: Create the final run image (your original base)
FROM openjdk:8-jre-alpine
WORKDIR /usr/src/app
# Copy the JAR from the 'build' stage
COPY --from=build /home/app/target/*.jar ./app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","./app.jar", "--port=80"]