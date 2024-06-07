FROM amazoncorretto:17-alpine-jdk
COPY target/demo-0.0.1-SNAPSHOT.jar java-api.jar
ENTRYPOINT ["java", "-jar", "java-api.jar"]