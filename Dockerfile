FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} BusTrackerService.jar
ENTRYPOINT ["java","-jar","/BusTrackerService.jar"]