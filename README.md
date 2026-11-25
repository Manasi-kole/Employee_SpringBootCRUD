# Employee Application

A basic CRUD REST API built with Spring Boot, Spring Data JPA and an in-memory H2 database.

## Tech
- Java 17
- Spring Boot
- Spring Data JPA
- H2 database
- Lombok
- JUnit 5 and Mockito

## How to run
```
./mvnw spring-boot:run
```
(On Windows: `mvnw.cmd spring-boot:run`.) The app starts on http://localhost:8080.

H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:employeedb`, user `sa`, empty password).

## Endpoints

| Method | URL | What it does |
|--------|-----|--------------|
| POST   | `/Employee/addEmployee` | Add an employee |
| GET    | `/Employee/getEmployee/{empid}` | Get one employee (404 if not found) |
| PUT    | `/Employee/updateEmployee` | Update an employee (404 if not found) |
| DELETE | `/Employee/deleteEmployee/{empid}` | Delete an employee (204, or 404 if not found) |

Example body for add and update:
```json
{
  "empid": 1,
  "fname": "Manasi",
  "lname": "K",
  "salary": 50000,
  "email": "manasi@example.com",
  "department": "IT"
}
```

## Run the tests
```
./mvnw test
```
