FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} go-travel-service-5.0.0.jar/
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/go-travel-service-5.0.0.jar"]
