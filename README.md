LIBRARY API

## Technology stack:
- Java 17
- Spring Boot 2.7.12
- Database (PostgreSQL)
- Spring Data JPA
- Flyway
- Spring Security (JWT)
- Mapper (MapStruct)
- Bean Validation
- Gradle
- Swagger
- Lombok

## Docker-compose(+ Postgres database)
### Run the following commands to build and run the application:
- git clone https://github.com/lisitsaaa/library.git
- cd library
- gradle bootJar
- docker-compose build
- docker-compose up -d
- docker-compose ps
- Mysql access port: 5433, Web port: 8080
- Swagger: http://localhost:8080/swagger-ui/index.html
