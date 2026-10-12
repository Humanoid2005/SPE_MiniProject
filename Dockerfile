FROM eclipse-temurin:21-jre-jammy

WORKDIR /server

COPY target/*.jar app.jar

EXPOSE 8000

CMD ["java", "-jar", "app.jar"]