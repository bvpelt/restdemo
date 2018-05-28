# restapi

See 
- https://stackoverflow.com/questions/28228068/spring-boot-full-rest-crud-example
- https://www.youtube.com/watch?v=zM5JEDpoUr0&feature=youtu.be
- dependencies https://repository.sonatype.org/#welcome 

# Rest
http://www.rfc-base.org/rfc-2616.html 

## PUT
Idempotent, change/update a resource with known uri

## POST
Create a new resource and add it to the collection

# Add an entry
``` 

```
result
``` 
Account Created Successfully
```

``` 
curl -v -H "Content-Type: application/json" -d '{"id": 2, "name": "account 2", "email": "account@test.com"}' http://localhost:8080/accounts/
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /accounts/ HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.47.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 59
> 
* upload completely sent off: 59 out of 59 bytes
< HTTP/1.1 201 
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 28
< Date: Fri, 04 May 2018 18:57:53 GMT
< 
* Connection #0 to host localhost left intact
```

``` 
curl -v http://localhost:8080/accounts/
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /accounts/ HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.47.0
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Fri, 04 May 2018 19:00:40 GMT
< 
* Connection #0 to host localhost left intact
[{"name":"account 1","id":1,"email":"account@test.com"},{"name":"account 2","id":2,"email":"account@test.com"}]
```

Update
``` 
curl -v -X PUT -H "Content-Type: application/json" -d '{"accountId": "3", "name": "account 33333", "email": "account@test.com"}' http://localhost:8080/accounts/3
```
# Database
- createdb restdemo
- createuser -P testuser 
- psql restdemo
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO testuser;
ALTER USER "testuser" WITH PASSWORD '12345';

# Adres

```
curl -H "Content-Type: application/json" -d '{"straatNaam": "Jan Steenlaan", "huisNummer": "33", "postCode": "3904XL", "woonPlaats": "VEENENDAAL", "telefoonNummer": "0318540607"}' http://localhost:8080/adresses/

```


http://www.baeldung.com/spring-data-rest-intro

http://www.baeldung.com/spring-data-rest-relationships

http://www.springboottutorial.com/spring-boot-hateoas-for-rest-services

https://howtodoinjava.com/spring/spring-boot2/rest-with-spring-hateoas-example/

http://www.springboottutorial.com/integration-testing-for-spring-boot-rest-services

cors https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Origin