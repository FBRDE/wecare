FROM amazoncorretto:11
EXPOSE 8081
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} patient-1.0.0.jar
ENTRYPOINT ["java","-jar","/patient-1.0.0.jar"]