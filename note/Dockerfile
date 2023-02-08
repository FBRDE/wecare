FROM amazoncorretto:11
EXPOSE 8082
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} note-1.0.0.jar
ENTRYPOINT ["java","-jar","/note-1.0.0.jar"]