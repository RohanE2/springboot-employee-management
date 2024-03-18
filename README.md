# Spring Boot Employee Management

This Spring Boot project provides RESTful API endpoints for managing employee records within an organization. It includes features such as adding, searching, updating, and removing employee data. Additionally, it offers functionality for creating admin accounts, logging in, and logging out.

## Endpoints

- **Add Employee:** `/add` (POST)
- **Search Employee:** `/search/{id}` (GET)
- **Remove Employee:** `/remove/{id}` (GET)
- **Update Employee:** `/update/{id}/{name}/{email}/{contact}/{designation}/{salary}` (POST)
- **Create Admin Account:** `/createadmin` (POST)
- **Login:** `/login/{username}/{password}` (POST)
- **Logout:** `/logout` (GET)

## Technologies Used

- Spring Boot
- Spring Data JPA
- Hibernate ORM
- MySQL database
- RESTful API design
- JSON for data exchange


## Project Structure

- **src/main/java/com/jspiders/springboot/controller:** Contains controller classes defining API endpoints.
- **src/main/java/com/jspiders/springboot/service:** Contains service classes for business logic.
- **src/main/java/com/jspiders/springboot/repository:** Contains repository interfaces for database operations.
- **src/main/java/com/jspiders/springboot/pojo:** Contains POJO (Plain Old Java Objects) classes representing entities.
- **src/main/resources:** Contains application properties, including database configuration.

## Dependencies

- Java Development Kit (JDK)
- Spring Boot
- Spring Data JPA
- Hibernate ORM
- MySQL JDBC driver
