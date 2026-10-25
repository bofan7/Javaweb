# Build the selected Java project first; copy its packaged jar here as app.jar.
# JDK installers are replaced with an official runtime image.
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY app.jar app.jar
EXPOSE 8080
# Pass OSS_ACCESS_KEY_ID / OSS_ACCESS_KEY_SECRET at runtime when needed.
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
