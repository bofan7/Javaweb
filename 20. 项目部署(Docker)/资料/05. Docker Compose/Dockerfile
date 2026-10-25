# Build the selected Java project first; copy its packaged jar here as tlias.jar.
# JDK installers are replaced with an official runtime image.
FROM eclipse-temurin:17-jre
WORKDIR /tlias
COPY tlias.jar tlias.jar
EXPOSE 8080
# Pass OSS_ACCESS_KEY_ID / OSS_ACCESS_KEY_SECRET at runtime when needed.
ENTRYPOINT ["java", "-jar", "/tlias/tlias.jar"]
