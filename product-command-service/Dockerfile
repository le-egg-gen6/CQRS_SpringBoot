FROM amazoncorretto:21-alpine3.17 AS BUILDER

WORKDIR /build

COPY .mvn/ ./.mvn
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw dependency:go-offline

COPY . .
COPY . .
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw package -DskipTests

FROM amazoncorretto:21-alpine3.17
WORKDIR /run
COPY --from=BUILDER /build/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/run/app.jar"]