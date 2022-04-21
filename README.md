# oze-hospital
### Technology Stack
- Java 11
- Spring Boot
- H2 DB: In-memory DB.
- Docker
- Apache commons CSV

### Description of service
The service is a set of REST API endpoints that allows for the user to 
- register as a staff
- login as a staff
- validated staff update own record as a staff
- validated staff get records patients (up to two year old records).
- validated staff can get record of specific patient in a csv format
- validated staff can delete multiple records within a provided time range.

***A validated staff is a staff that must have been registered and consequently logged in at the time they are about using the system.***

### Constraints and Assumptions
- Staff are required to register and log in before they can have access to some of the protected endpoints.
- The login requires the staff name and password that must have been provided at the point of registration.
- A staff can only update his/her own record. 
- As stated in the requirement, when fetching records of patients, only patients with last visit date within the last two (2) years are fetched.

### Starting Application
The application has been packaged in a docker container. 
Navigate to the root of the application and start the application with the following commands
```
mvn clean package
docker-compose up
```

The application will start on port 5050.
### Testing
Once the application starts, navigate to 
[http://localhost:5050/swagger-ui](http://localhost:5050/swagger-ui.html) on a web-browser. 
The available endpoints can be tested from the loaded Swagger Interface



