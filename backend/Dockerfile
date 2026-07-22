#==========================
# ETAPA 1 - Builder
#==========================
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY settings.gradle .
COPY build.gradle .

RUN chmod +x gradlew

# Descarga dependencias
RUN ./gradlew dependencies --no-daemon

COPY src src

# Genera el bootJar
RUN ./gradlew clean bootJar --no-daemon -x test

#==========================
# ETAPA 2 - Runtime
#==========================
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]