FROM openjdk:alpine
VOLUME /tmp
ADD /maven/spring-boot-docker.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080
