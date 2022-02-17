FROM maven:3.8.4-jdk-11
COPY ./ ./
RUN mvn clean package
CMD ["java", "-jar", "target/conference-0.0.1-SNAPSHOT.jar"]
