# Etapa de construcción usando Java 17
FROM eclipse-temurin:17-jdk as build

COPY . /app
WORKDIR /app

RUN chmod +x mvnw && ./mvnw package -DskipTests
RUN mv target/*.jar app.jar || exit 1

# Etapa de ejecución usando Java 17
FROM eclipse-temurin:17-jre

ARG PORT
ENV PORT=${PORT}

COPY --from=build /app/app.jar .

ENTRYPOINT [ "java", "-Dserver.port=${PORT}", "-jar", "app.jar" ]
