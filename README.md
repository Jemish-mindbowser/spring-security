## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`

```properties
spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false&createDatabaseIfNotExist=true
spring.datasource.username= root
spring.datasource.password= Root@123

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# JWT tokem properties
myapp.app.jwtSecret= mySecretKey
myapp.app.jwtExpirationMs= 86400000
```

## Run Spring Boot application
```
mvn spring-boot:run
```

## Run following SQL insert statements
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

## API documentation with Swagger-UI
VISIT : http://localhost:8080/swagger-ui.html

1) Register User API
METHOD TYPE : POST
URL : http://localhost:8080/api/auth/register
PAYLOAD : 
        {
            "email": "test@gmail.com",
            "password": "test@123",
            "username": "test"
        }

2) Signin API
METHOD TYPE : POST
URL : http://localhost:8080/api/auth/signin
PAYLOAD : 
        {
            "password": "test@123",
            "username": "test"
        }
Response : 
        {
        "error": null,
        "data": {
            "jwt": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjI0OTQxNjQ2LCJleHAiOjE2MjUwMjgwNDZ9.qVoxdMZqARpO1Z7IkTOYhhcTnMGcWkcuokWAm1YNXalIeZ9vKiVcN4kr7b0XOrib9KM5o_3CYwT2HISAfQk6ew",
            "roles": [
            "ROLE_USER"
            ],
            "id": 1,
            "email": "test@gmail.com",
            "username": "test"
        },
        "statusCode": 200,
        "message": "Success"
        }

User Authorize button on swagger-ui on right top corner to set your JWT token for calling secure APIs
add your token with "Bearer "
