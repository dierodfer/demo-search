# demo-search
Demo project for search algorithm with PostgreSQL container

## Requirements
- JDK >= 11
- Docker

## Compile maven depedencies
```
./mvnw clean install -DskipTests -s .mvn/wrapper/settings.xml       #Linux
mvnw.cmd clean install -DskipTests -s .mvn/wrapper/settings.xml     #Windows
```

## Database container
```
docker-compose down & docker-compose up -d
```

## Database connection
```
address: localhost:5432
DB: demo
user: demo
password: root
```

## Start application
```
./mvnw spring-boot:run      #Linux
mvnw.cmd spring-boot:run    #Windows
```


## Unit Tests
```
./mvnw test     #Linux
mvnw.cmd test   #Windows
```
