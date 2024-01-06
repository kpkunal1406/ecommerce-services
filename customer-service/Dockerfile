# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

# Package stage
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/customer-service-0.0.1-SNAPSHOT.jar /usr/local/lib/customer-service.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","/usr/local/lib/customer-service.jar"]