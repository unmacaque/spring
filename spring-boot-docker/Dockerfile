FROM openjdk:11-jre-slim AS build
WORKDIR /opt
COPY /target/spring-boot-docker.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract
FROM openjdk:11-jre-slim
VOLUME /tmp
WORKDIR /app
COPY --from=build /opt/dependencies/ ./
COPY --from=build /opt/spring-boot-loader/ ./
COPY --from=build /opt/snapshot-dependencies/ ./
COPY --from=build /opt/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080
