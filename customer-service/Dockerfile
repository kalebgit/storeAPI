# compila el proyecto con maven
FROM maven:3.9-eclipse-temurin-21 AS build
# directorio de trabajo dentro del contenedor
WORKDIR /app
# copiamos el pom primero para cachear dependencias
COPY pom.xml .
# descargamos dependencias antes de copiar el codigo fuente
RUN mvn dependency:go-offline -q
# copiamos el codigo fuente
COPY src ./src
# compilamos y generamos el jar, sin correr tests
RUN mvn package -DskipTests -q

# imagen final solo con el jar y jre (sin maven ni codigo fuente)
FROM eclipse-temurin:21-jre-alpine
# instalamos curl para los healthchecks de docker
RUN apk add --no-cache curl
WORKDIR /app
# copiamos solo el jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# comando que se ejecuta al arrancar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
