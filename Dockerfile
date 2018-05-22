### Build step ###
FROM maven:3.5-jdk-8-alpine as builder

RUN apk update && apk add xmlstarlet

WORKDIR /opt/mink/build
COPY . .
RUN mvn package -DskipTests \
    && cp "target/$(xml sel -N x=http://maven.apache.org/POM/4.0.0 -t -m x:project -v x:artifactId -o - -v x:version pom.xml)-exec.jar" app.jar

### Application image ###
FROM openjdk:8-jre-alpine

WORKDIR /opt/mink/run
COPY --from=builder "/opt/mink/build/app.jar" .

# Expose Spring Boot default port (8080)
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
