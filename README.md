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


___________


### Build docker image to push to dockerhub

docker build -t nhlinhseuit/identity-service:0.9.0 .

     // build for specific ec2 platform: 
        docker buildx build --platform linux/amd64 -t nhlinhseuit/identity-service:0.9.0 --push .


### Push docker image to Docker Hub

docker image push nhlinhseuit/identity-service:0.9.0

### Run container after pulling image (after run container postgres) (different from line 48 the image name and tag)

docker run --name identity-service --network devteria-network -p 8080:8080 -e DBMS_CONNECTION=jdbc:postgresql://postgres:5432/identity_service nhlinhseuit/identity-service:0.9.0


___________


### Deploy to AWS (## Install Docker on ubuntu)

# B1. SSH to EC2 by Termius
# B2. Install Docker to ubuntu

# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
"deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
$(. /etc/os-release && echo "$VERSION_CODENAME") stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sudo docker run hello-world

# B3. Create network > Pull and run postgres 

# B4. Vào ec2 vào security > Security group > Inbound rules > Exposed port 5432 của postgres ra > Để test connect tới postgres được chưa -> Connect bằng DBeaver

# B5. Pull and run identity-service from my dockerhub

sudo docker pull nhlinhseuit/identity-service:0.9.0

docker run --name identity-service --network devteria-network -p 8080:8080 -e DBMS_CONNECTION=jdbc:postgresql://postgres:5432/identity_service nhlinhseuit/identity-service:0.9.0

     // If ubuntu show 'no matching manifest for linux/amd64' => build image for ubunto platform
        docker buildx build --platform linux/amd64 -t nhlinhseuit/identity-service:0.9.0 --push .
