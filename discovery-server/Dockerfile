# Build stage
FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app

# Copy Maven files first for better layer caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Add execute permissions to Maven wrapper and download dependencies
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline

# Copy source code and build
COPY src src
RUN ./mvnw clean package -DskipTests

# Extract layers for better caching
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/app/target/dependency

# Copy layers from builder stage
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app

# Set the entrypoint
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.yourcompany.Application"]