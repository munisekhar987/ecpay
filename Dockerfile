# Use a base image with JDK
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/myapp.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
