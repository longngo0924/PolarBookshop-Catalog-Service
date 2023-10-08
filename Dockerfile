FROM amazoncorretto:17-alpine-jdk
VOLUME /tmp
ARG JAR_FILE
COPY target/catalog-service-0.0.1-SNAPSHOT.jar catalog-service.jar
ENTRYPOINT ["java","-jar","/catalog-service.jar"]