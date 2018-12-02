FROM openjdk:11-jdk-slim AS build
WORKDIR /opt
ADD /target/spring-boot-docker.jar app.jar
RUN jar -xf *.jar
FROM openjdk:11-jre-slim
VOLUME /tmp
COPY --from=build /opt/BOOT-INF/lib /app/lib
COPY --from=build /opt/META-INF /app/META-INF
COPY --from=build /opt/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp", "app:app/lib/*", "com.gmail.unmacaque.spring.Application"]
EXPOSE 8080
