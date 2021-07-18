FROM adoptopenjdk/maven-openjdk11 AS build
WORKDIR /build
COPY . .
RUN mvn clean install -U -DskipTests
RUN pwd
RUN cp /build/target/kotlin-spring-boot*.jar /app.jar
RUN ls -la

FROM adoptopenjdk/openjdk11 AS base
ENV LANG C.UTF-8
WORKDIR /app
COPY --from=build /app.jar .
CMD java -jar -Dspring-profiles.active=production /app/app.jar