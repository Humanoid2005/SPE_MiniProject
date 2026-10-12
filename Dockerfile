# Stage 1: compile and package the application.
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /build
COPY pom.xml .

# Cache dependencies separately from application source changes.
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

# Stage 2: run the application using only the JRE and built JAR.

FROM eclipse-temurin:21-jre-jammy

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /server

# creating a non-root user
RUN useradd --system --user-group calculator
COPY --from=build --chown=calculator:calculator /build/target/calculator.jar app.jar
USER calculator

EXPOSE 8000

CMD ["java", "-jar", "app.jar"]