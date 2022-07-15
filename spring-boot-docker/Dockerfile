FROM eclipse-temurin:17-alpine AS build
RUN apk update && apk add binutils
WORKDIR /opt
COPY *.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract
RUN jlink --output runtime --no-header-files --no-man-pages --strip-debug --compress=0 --add-modules ALL-MODULE-PATH

FROM alpine:3.16
WORKDIR /app
COPY --from=build /opt/runtime/ /usr/local/
COPY --from=build /opt/dependencies/ ./
COPY --from=build /opt/spring-boot-loader/ ./
COPY --from=build /opt/application/ ./
RUN addgroup -S java && adduser -S java -G java
USER java
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080
