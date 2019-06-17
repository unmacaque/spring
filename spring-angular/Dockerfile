FROM node:lts-alpine AS ng-build
WORKDIR /ng
COPY ng .
RUN npm install
RUN npx ng build --prod

FROM openjdk:11-jdk-slim AS build
WORKDIR /src
COPY /target/spring-angular.jar app.jar
RUN jar -xf *.jar

FROM openjdk:11-jre-slim
VOLUME /tmp
COPY --from=build /src/BOOT-INF/lib /app/lib
COPY --from=build /src/META-INF /app/META-INF
COPY --from=build /src/BOOT-INF/classes /app
COPY --from=ng-build /ng/dist/spring-angular /app/static
ENTRYPOINT ["java","-cp", "app:app/lib/*", "com.gmail.unmacaque.spring.Application"]
EXPOSE 8080
VOLUME /root/.embedmongo
