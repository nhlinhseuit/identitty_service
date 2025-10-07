# Identity service

This microservice is responsible for:

- Onboarding users
- Roles and permissions
- Authentication

## Tech stack

- Build tool: maven >= 3.9.5
- Java: 21
- Framework: Spring boot 3.2.x
- DBMS: MySQL

## Prerequisites

- Java SDK 21
- A MySQL server

## Start application

`mvn spring-boot:run`

## Build application

`mvn clean package`

## Build image

docker build -t identity-service:0.0.1 .

### B1. Create network:

docker network create devteria-network

### B2. Start Postgres in devteria-network

<!-- docker run --network devteria-network --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.36-debian -->

docker run --network devteria-network --name postgres \
 -p 5432:5432 \
 -e POSTGRES_PASSWORD=1234 \
 -e POSTGRES_USER=postgres \
 -e POSTGRES_DB=identity_service \
 -d postgres:16

### B3. Run your application in devteria-network

docker run --name identity-service --network devteria-network -p 8080:8080 -e DBMS_CONNECTION=jdbc:postgresql://postgres:5432/identity_service identity-service:0.0.1

### Res: postgres container && identity-service container in the same network