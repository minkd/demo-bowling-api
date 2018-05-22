# Bowling Service

This is a Java Spring Boot application (version 2.0.2). The purpose of this service is to demo a bowling api. This project uses the spring boot actuator for production metrics and distributed tracing.

## How to Run

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:

        java -jar target/app.jar (replace app with actual jar file name within the target dir)
or
        mvn spring-boot:run
        
* Check the stdout to make sure no exceptions are thrown. 

Once the application runs you should see something like this

```
2018-03-20 11:56:49.185  INFO 2318 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2018-03-20 11:56:49.188  INFO 2318 --- [           main] .c.CouponConfigurationServiceApplication : Started CouponConfigurationServiceApplication in 6.072 seconds (JVM running for 6.671)
```

### Notes about docker

This application includes a method of execution using Docker. 

The application is built using the Spring Boot Web default port of 8080, which is exposed in the Dockerfile.

## About the Service

The service is a RESTful API which provides routes to appropriate & configure a bowling game. 

### Get information about system health, configurations, etc.

```
http://localhost:8080/actuator/env
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/prometheus
```

### API Documentation

This project adheres to the  the [Open API Specification](https://www.openapis.org/) for API documentation which is also avaliable locally via (localhost:8080/swagger-ui.html). Note that this UI will not be available in deployed instances and is for demonstration purposes only 

## Attaching to the app remotely from your IDE

Run the service with these command line options:

```
mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
or
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Dspring.profiles.active=test -Ddebug -jar target/app.jar
```
and then you can connect to it remotely using your IDE. For example, from IntelliJ You have to add remote debug configuration: Edit configuration -> Remote.

## Contributing

### Issues 

Feel free to submit issues and enhancement requests. 

### Contributing

Please refer to the project style & architecture guidelines in each layer of the API. The CCS has three layers, each with 
specific responsibilities:

1. Resource Layer
    - Uses Java DTO objects to represent data transfer from user to api 
    - Uses Open API annotations to synthesize documentation
2. Service Layer     
    - Middleman between the model and the resource using Mappers
    - Implements mappers to convert data recieved from the model into DTO objects which can be serialized and used for 
    external API interaction

## Questions and Comments: 

Send To: [Derrick Mink](derrick.mink@gmail.com) 





