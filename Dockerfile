FROM eclipse-temurin:18-jdk-alpine
VOLUME /api
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]