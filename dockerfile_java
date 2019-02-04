FROM openjdk:11.0.1-jre-slim-stretch
ARG JAR=java-logging-1.0.jar
COPY build/libs/$JAR /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
