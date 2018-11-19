# ---- Build ----
FROM maven:3-jdk-11-slim AS build

# Cache maven depandencies
COPY api/pom.xml /usr/src/app/api/pom.xml
COPY models/pom.xml /usr/src/app/models/pom.xml
COPY services/pom.xml /usr/src/app/services/pom.xml
COPY pom.xml /usr/src/app/pom.xml

# dependency:go-offline doesn't work
RUN mvn -f /usr/src/app/pom.xml clean package

# Build application
COPY api/src /usr/src/app/api/src
COPY models/src /usr/src/app/models/src
COPY services/src /usr/src/app/services/src

RUN mvn -f /usr/src/app/pom.xml clean package

# ---- Run ----
FROM openjdk:11-jre-slim

COPY --from=build /usr/src/app/api/target/track-metadata-api-*.jar /app/track-metadata-api.jar

WORKDIR /app

EXPOSE 8080 5005

# For remote debugging use following environemnt variable:
# JAVA_TOOL_OPTIONS: -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=<service-name>:5005
CMD java -jar track-metadata-api.jar