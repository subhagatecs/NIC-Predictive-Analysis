# Description
This is a Spring Boot microservice, i.e. a POST method to save either CSV or Excel records in local database. The database used here is PostgreSQL.



# Microservice
Here the microservice is included in the folder CSVDemo. Here the dependencies used are Spring Web, Spring Data JPA, Spring Boot DevTools and PostgreSQL driver. Under the main folder in src, the file CsvDemoApplication.java runs the Spring Boot Application. There are 5 packages used along with it :- 

### Controller:-
It contains the RESTful controller classes that handles the incoming http requests and define the endpoints.

### Entity:-
It stores data models or entities that represent structure and behaviour of the application domain. These classes are mapped to database tables and define the properties and relationships of the application data.

### Repository:-
This contains repository classes that deals with data access. It uses JPA(Java Persistant API) to interact with database.

### Response:-
It contains the response format of the user's API request.

### Service:-
It contains service classes to implement logic and it is used by controller to perform operations on data.

The folder resources contains the file application.properties in which the database url, username and password is configured as per local database.



# CSV 
Here the file test.csv is used from which the microservice extracts the data and stores in local database.
