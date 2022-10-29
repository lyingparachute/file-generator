# File generator

### Web application for local file and file in database creation. App provides REST endpoints

## HOW TO RUN APP:

### 1. Create Docker image

* Make sure you don't have any existing images with: `docker ps`
* If so, then run:
* `docker stop $(docker ps -aq)`
* `docker rm $(docker ps -aq)`
* run command to create
  image: `docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=toor -e MYSQL_DATABASE=filecreator --rm -d mysql`

### 2. Build project and perform tests

* Open terminal in project directory
* Type:
  `mvn clean install`

### 3. Start application

* `mvn spring-boot:run`
* Press CTRL+C to finish running app
