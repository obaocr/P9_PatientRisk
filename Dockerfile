FROM openjdk:8-jdk-alpine
LABEL responsable="o.barberis@outlook.fr"
EXPOSE 8052:8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} patientRisk.jar
ENTRYPOINT ["java","-jar","/patientRisk.jar"]