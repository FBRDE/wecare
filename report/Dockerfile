FROM amazoncorretto:11
EXPOSE 8084
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} report-1.0.0.jar
ENTRYPOINT ["java","-jar","/report-1.0.0.jar"]