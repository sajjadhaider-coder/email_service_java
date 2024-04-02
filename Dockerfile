FROM openjdk:20
EXPOSE 8081
ADD target/cinqd_email_service.jar cinqd_email_service.jar
ENTRYPOINT ["java", "-jar", "/cinqd_email_service.jar"]