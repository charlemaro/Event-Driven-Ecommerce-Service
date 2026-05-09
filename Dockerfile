# ─── Stage 1: Build ─────────────────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copy pom.xml first — Docker caches this layer so dependencies aren't
# re-downloaded on every build unless pom.xml actually changes
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copy source and build the JAR (tests run in CI, not here)
COPY src ./src
RUN mvn package -DskipTests -q

# ─── Stage 2: Runtime ─────────────────────────────────────────────────────────
# Lean JRE-only image — no Maven, no source code, just what's needed to run
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]