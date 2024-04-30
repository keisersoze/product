#
# Build stage
#
FROM maven:3.9.6-amazoncorretto-17-al2023 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN --mount=type=cache,target=/root/.m2 mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM amazoncorretto:21.0.2-alpine3.19
COPY --from=build /home/app/target/product-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]