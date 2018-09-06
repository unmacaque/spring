FROM openjdk:8-alpine
VOLUME /tmp
RUN apk add --no-cache curl
ADD /target/spring-boot-docker.jar app.jar
RUN sh -c 'touch /app.jar'
HEALTHCHECK --start-period=10s CMD curl --fail http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080
