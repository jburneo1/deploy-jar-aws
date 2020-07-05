FROM mysql:5.7.24
LABEL maintainer="El Yisus"
#WORKDIR /workspace
#Podemos usar ADD o Copy
#ADD scripts/DDL.sql /docker-entrypoint-initdb.d/DDL.sql
ENV MYSQL_ROOT_PASSWORD=toor
ENV MYSQL_ROOT_DATABASE=demobd
ENV MYSQL_ROOT_HOST=%

COPY ./scripts /docker-entrypoint-initdb.d/
CMD ["--default-authentication-plugin=mysql_native_password"]