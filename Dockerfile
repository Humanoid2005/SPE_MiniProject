FROM eclipse-temurin:21-jre-jammy

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /server

COPY target/*.jar app.jar

EXPOSE 8000

CMD ["java", "-jar", "app.jar"]
