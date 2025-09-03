# syntax=docker/dockerfile:1.7

########## Build (Maven) ##########
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only build descriptors first for better cache hits
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .

# Warm the Maven cache (runs only when pom.xml/mvnw/.mvn change)
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw -q -DskipTests dependency:go-offline

# Now copy sources; only this invalidates when you edit code
COPY src ./src

# Compile/package (reuses the same ~/.m2 cache)
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw -q -DskipTests -T 1C package

########## Runtime (small JRE, non-root optional later) ##########
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
