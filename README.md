# EDTS Technical Test

![java](https://img.shields.io/badge/Java-11-orange)
![springboot](https://img.shields.io/badge/Spring%20Boot-2.7.12-brightgreen)
<br>

### Requirements

- Java 11
- Maven 3
- MySQL

### Running the application locally

#### Load your .env

- Get from .env.example to setup your own .env property
- Adjust the Database connection to your own connection
```
DATABASE_HOST=localhost
DATABASE_PORT=3306
DATABASE_NAME=edts-employee
DATABASE_USERNAME=root
DATABASE_PASSWORD=root
```
  <br>

#### Build Application & Install all dependencies
Unix

```shell
./mvnw clean install
```

Windows

```shell
mvnw.cmd clean install
```

  <br>

#### Run Project

Unix

```shell
./mvnw spring-boot:run
```

Windows

```shell
mvnw.cmd spring-boot:run
```

