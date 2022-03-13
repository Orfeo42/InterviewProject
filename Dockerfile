FROM openjdk:17-alpine
ADD targer/user-api.jar user-api.jar
ENTRYPOINT ["java", "-jar", "user-api.jar"]