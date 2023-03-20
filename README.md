EV Station Crud Application

Used Stack:

1. Java 8
2. Spring boot 2.7.8
3. Gradle 7.6
4. H2 as inmemory database.

Steps to Run the project.

1. Import the project in any of the IDE and build the project.
2. Run the application.
3. Backend API will be up and running in port 8080

Avaialable Endpoints

1. Get All Stations - GET : http://localhost:8080/
   ( by default 10 will be the limit and sorted by stationId in ascending order)
2. Get particular Station by Id - GET : http://localhost:8080/show/{StationId}
3. Add New Station - POST : http://localhost:8080/
4. Update the Station by Id - PUT : http://localhost:8080/{StationId}/edit
5. Delete the Station by Id - DELETE : http://localhost:8080/delete/{StationId}
   


