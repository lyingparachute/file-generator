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

### 4. Test REST endpoints with Postman

* Create file with 500.000 result Strings

![](https://github.com/lyingparachute/file-generator/blob/master/src/main/resources/postman/post_500000.png)
![](https://github.com/lyingparachute/file-generator/blob/master/src/main/resources/postman/post_500000_response.png)

* Create file with 5 result Strings

![](https://github.com/lyingparachute/file-generator/blob/master/src/main/resources/postman/post_5.png)
![](https://github.com/lyingparachute/file-generator/blob/master/src/main/resources/postman/post_5_response.png)

* Get IDs of all jobs

![](https://github.com/lyingparachute/file-generator/blob/master/src/main/resources/postman/get_api-files-jobs.png)

* Get all jobs' values

![](https://github.com/lyingparachute/file-generator/blob/master/src/main/resources/postman/get_api-files.png)

* Observe in Logs that file with 5 String (2.txt) was created before file with 500.000 Strings (1.txt)

![](https://github.com/lyingparachute/file-generator/blob/master/src/main/resources/postman/logs.png)
