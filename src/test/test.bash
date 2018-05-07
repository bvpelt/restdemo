#!/usr/bin/env bash

# fill database with 5 entries
curl -v -H "Content-Type: application/json" -X POST -d '{"id": 1, "name": "account 1", "email": "account@test.com"}' http://localhost:8080/accounts/
curl -v -H "Content-Type: application/json" -X POST -d '{"id": 2, "name": "account 2", "email": "account@test.com"}' http://localhost:8080/accounts/
curl -v -H "Content-Type: application/json" -X POST -d '{"id": 3, "name": "account 3", "email": "account@test.com"}' http://localhost:8080/accounts/
curl -v -H "Content-Type: application/json" -X POST -d '{"id": 4, "name": "account 4", "email": "account@test.com"}' http://localhost:8080/accounts/
curl -v -H "Content-Type: application/json" -X POST -d '{"id": 5, "name": "account 5", "email": "account@test.com"}' http://localhost:8080/accounts/

# get all accounts
curl -v http://localhost:8080/accounts/

# get account 1, 5
curl -v http://localhost:8080/accounts/1
curl -v http://localhost:8080/accounts/5