# ---- Build ----
FROM maven:3-jdk-11-slim AS build

COPY api/src /usr/src/app/api/src
COPY api/pom.xml /usr/src/app/api
COPY models/src /usr/src/app/models/src
COPY models/pom.xml /usr/src/app/models
COPY services/src /usr/src/app/services/src
COPY services/pom.xml /usr/src/app/services
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package


# ---- Run ----
FROM openjdk:11-jre-slim

COPY --from=build /usr/src/app/api/target/track-metadata-api-*.jar /app/track-metadata-api.jar

WORKDIR /app

EXPOSE 8080

CMD java -jar track-metadata-api.jar