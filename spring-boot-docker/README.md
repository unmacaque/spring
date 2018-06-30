# spring-boot-docker

1. Run `mvn package` to build a JAR file of the Spring Boot app
1. Run `mvn docker:build` to build a Docker image of the app
1. Execute `docker run -p 8080:8080 -t unmacaque/spring-boot-docker` to start the containerized app
