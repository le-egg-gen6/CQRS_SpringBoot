FROM maven:3.9.4-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

RUN apk add --no-cache nginx

WORKDIR /app

COPY --from=build /app/target/*.jar application.jar

COPY nginx/nginx.conf /etc/nginx/nginx.conf
COPY script/start.sh /app/start.sh

RUN chmod +x /app/start.sh

EXPOSE 80

CMD ["/app/start.sh"]