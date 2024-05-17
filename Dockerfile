FROM openjdk:17-jdk-slim
VOLUME /thecatapi
EXPOSE  2015
ADD target/app-thecat-api-0.1.jar app-thecat-api-0.1.jar
ENTRYPOINT ["java", "-jar", "/app-thecat-api-0.1.jar"]