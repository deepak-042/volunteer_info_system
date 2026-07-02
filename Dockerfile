FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY build/libs/volunteer_info_system-0.0.1-SNAPSHOT.jar volunteer_info_system-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java" , "-jar" , "/app/volunteer_info_system-0.0.1-SNAPSHOT.jar"]