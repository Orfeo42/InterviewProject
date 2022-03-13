FROM openjdk:17-alpine
ADD target/user-api.jar user-api.jar
ENTRYPOINT ["java", "-jar", "user-api.jar"]