# inventory-manager

Demo spring boot 2.0 webflux with reactive redis

default redis server is running on localhost:6379

spring:
  application:
    name: matrix-inventory-manager-local

http://localhost:8080/item/{itemName}
request for one specific item

spring:
  application:
    name: matrix-inventory-manager-local
  redis:
    host: localhost
    port: 6379 

server:
  port: 8080

