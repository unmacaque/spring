# Instructions

1.  Run `mvn package` to build a JAR file of the Spring Boot app
2.  Run `mvn docker:build` to build a Docker image of the app
3.  Execute `docker run -p 8080:8080 -t unmacaque/spring-boot-docker` to start the containerized app