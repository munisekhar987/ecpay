# Use a base image with JDK
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/myapp.jar user-module-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/user-module-0.0.1-SNAPSHOT.jar"]
