FROM openjdk:11
EXPOSE 8080
ADD target/userApi.jar userApi.jar
ENTRYPOINT ["java","-jar","/userApi.jar"]