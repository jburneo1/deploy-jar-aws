#### CONSTRUCCION MVN PACKAGE (JAR) ####
FROM maven:3.6-jdk-8-alpine AS builder

WORKDIR /app

COPY pom.xml .
#RUN mvn dependency:resolve
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline

COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

#### EJECUCION DEL JAR ####
FROM openjdk:8-jdk-alpine
LABEL maintainer="jeburneog@gmail.com"
WORKDIR /workspace

COPY --from=builder /app/target/api*.jar app.jar

ENV url_apipersona="apipersona"
ENV port_apipersona="8084"
ENV host="mysql_server"
ENV port="3306"
ENV database="demobd"
ENV username="root"
ENV password="toor"

ENV DOCKERIZE_VERSION v0.6.1
#Podemos descargar dockerize o copiarlo desde un directorio
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /workspace/app.jar
EXPOSE 8084