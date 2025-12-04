FROM eclipse-temurin:21-jdk-alpine-3.21

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "sleep 30 && java -jar target/braseducrm-0.0.1-SNAPSHOT.jar"]

