Cake Manager Micro Service
---

This repository consists of a Cake Management Application which can be used to load the Cakes from https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json
and load them into H2 in memory DB and list the Cakes, and authenticated user will be able to add new cakes.

How to run the Application:
----
User will be able to start the application using one of the below steps :
1. mvn spring-boot:run
2. run the CakeManagerApplication Class in IDE

Authentication Process for user to use POST Endpoint: 
---
1. Go to Swagger URL : http://localhost:8082/swagger-ui/index.html 
2. Click the Authorize button on the top right and enter the credentials (in application.yml) 
3. then go to POST endpoint and click Try it out
4. or can copy the Bearer token generated in Swagger and use in Postman to do the same 


Endpoints:
---
* localhost:8082:  load the Cakes from the server to DB.
* localhost:8082/cakes (GET) : Display all the Cakes in JSON
* localhost:8082/api/Cakes (POST) : User after authenticated , should be able to add a new Cake - (use Swagger)

