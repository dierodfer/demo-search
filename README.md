# demo-search
Demo project for search algorithm with H2 in memory embedded 

## Requirements
- JDK >= 11
- Maven

## Compile
```
mvn clean install -s .mvn/wrapper/settings.xml
```

## Start application
```
mvn spring-boot:run
```

## Access H2
```
http://localhost:8080/h2-console/
URL: jdbc:h2:mem:demosearch
user: SA (no password)
```


## Unit Tests
```
mvn test
```
