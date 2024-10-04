FROM maven:3.3.1-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17
EXPOSE 8075
ADD target/testpfe-0.0.1-SNAPSHOT.jar testpfe-0.0.1.jar
ENTRYPOINT ["java","-jar","/testpfe-0.0.1.jar"]
