# Syntax directive obligatoria para activar BuildKit / Cache Mounts
# syntax=docker/dockerfile:1.4

#==========================
# ETAPA 1 - Builder
#==========================
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# 1. Copiar primero ÚNICAMENTE los archivos de definición de dependencias
COPY gradlew .
COPY gradle gradle
COPY settings.gradle build.gradle ./

RUN chmod +x gradlew

# 2. Descargar dependencias aprovechando la caché de capas de Gradle y BuildKit.
# El --mount=type=cache reutiliza la carpeta de caché entre distintas ejecuciones de 'docker build'
RUN --mount=type=cache,target=/root/.gradle/caches \
    ./gradlew dependencies --no-daemon

# 3. Recién ahora copiamos el código fuente (que cambia frecuentemente)
COPY src src

# 4. Generar el bootJar reusando el caché de Gradle
RUN --mount=type=cache,target=/root/.gradle/caches \
    ./gradlew bootJar --no-daemon -x test

#==========================
# ETAPA 2 - Runtime (Inalterada, liviana)
#==========================
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]