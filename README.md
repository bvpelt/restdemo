# restapi

See 
- https://stackoverflow.com/questions/28228068/spring-boot-full-rest-crud-example
- https://www.youtube.com/watch?v=zM5JEDpoUr0&feature=youtu.be

# Rest
http://www.rfc-base.org/rfc-2616.html 

## PUT
Idempotent, change/update a resource with known uri

## POST
Create a new resource and add it to the collection

# Add an entry
``` 
curl -H "Content-Type: application/json" -X POST -d '{"id": 1, "name": "account 1", "email": "account@test.com"}' http://localhost:8080/accounts/
```
result
``` 
Account Created Successfully
```

``` 
curl -v -H "Content-Type: application/json" -X POST -d '{"id": 2, "name": "account 2", "email": "account@test.com"}' http://localhost:8080/accounts/
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
# Database
- createdb restdemo
- createuser -P testuser 
- psql restdemo
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO testuser;
ALTER USER "testuser" WITH PASSWORD '12345';
