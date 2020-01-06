FROM openjdk:8
ADD target/assigment-spring.jar assigment-spring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "assigment-spring.jar"]