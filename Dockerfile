# =============================================
# Dockerfile - package the app as a container image
# =============================================
# Build the jar first:   mvn package
# Build the image:       docker build -t ghcr.io/dev48v/springboot-from-zero:1.0.0 .
# Run it anywhere:       docker run -p 8080:8080 ghcr.io/dev48v/springboot-from-zero:1.0.0
#
# KEY CONCEPTS:
# - The image bundles a slim Java runtime + your jar: runs identically on any machine
# - JRE (not JDK) base keeps the image small - we only need to RUN, not compile
# - A non-root user is a production security basic
# - The OCI label links this image to its GitHub repo on ghcr.io

FROM eclipse-temurin:17-jre-alpine

LABEL org.opencontainers.image.source="https://github.com/dev48v/springboot-from-zero"
LABEL org.opencontainers.image.description="Spring Boot from Zero - learning app packaged as a container"

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY target/springboot-from-zero-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
