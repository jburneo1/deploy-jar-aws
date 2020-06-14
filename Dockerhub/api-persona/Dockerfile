FROM openjdk:8-jdk-alpine

LABEL maintainer="jeburneog@gmail.com"

WORKDIR /workspace

COPY target/api*.jar app.jar

ENV host="dns_base_datos"
ENV port="3306"
ENV database="demobd"
ENV username="root"
ENV password="toor"

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /workspace/app.jar
EXPOSE 8084