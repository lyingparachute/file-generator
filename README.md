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

![](../../../Pictures/Screenshots/Screenshot from 2022-10-29 16-19-26.png)
![](../../../Pictures/Screenshots/Screenshot from 2022-10-29 16-27-45.png)

* Create file with 5 result Strings

![](../../../Pictures/Screenshots/Screenshot from 2022-10-29 16-19-33.png)
![](../../../Pictures/Screenshots/Screenshot from 2022-10-29 16-25-20.png)

* Get IDs of all jobs

![](../../../Pictures/Screenshots/Screenshot from 2022-10-29 16-19-53.png)

* Get all jobs' values

![](../../../Pictures/Screenshots/Screenshot from 2022-10-29 16-19-57.png)

* Observe in Logs that file with 5 String (2.txt) was created before file with 500.000 Strings (1.txt)

![](../../../Pictures/Screenshots/Screenshot from 2022-10-29 16-31-17.png)
