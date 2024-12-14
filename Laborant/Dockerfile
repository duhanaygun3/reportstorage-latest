FROM openjdk:22
WORKDIR /app
ADD target/Laborant-0.0.1-SNAPSHOT.jar Laborant-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","Laborant-0.0.1-SNAPSHOT.jar"]
