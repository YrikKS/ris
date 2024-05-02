FROM amazoncorretto:21-alpine
LABEL authors="bel_p"

ARG JAR_FILE=build/libs/manager-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]