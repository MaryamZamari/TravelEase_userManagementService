# TravelEase User Management Service

## Project Description

The TravelEase User Management Service is a backend service that handles user management, as a part of a Microservices project_ TravelEase Project. This project is created for implementation of what I have been learning during my self-learning process.
It provides features for user registration, authentication, and role-based authorization. This service ensures secure access to the application and proper management of user accounts.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- SQL Server
- RESTful APIs
- BCrypt for password hashing

## Features

- User Registration: New users can register their accounts with the service.
- User Authentication: Registered users can log in using their credentials.
- Role-Based Authorization: Users are assigned roles (e.g., ADMIN, USER), controlling their access to specific features/endpoints.
- Secure Password Storage: Passwords are securely hashed before storage.
- Authentication: based on user credentials already persisted in database. 

## Installation

1. Clone the repository to your local machine:

   ```
   git clone https://github.com/your-username/TravelEase-User-Management-Service.git
 
Open the project in your favorite Integrated Development Environment (IDE).

Configure the application properties, including the database connection details, in src/main/resources/application.properties.

Build and run the application:

```./mvnw spring-boot:run ```


## Configuration
Database Configuration: Customize the database connection settings in application.properties.
Security Configuration: Adjust security settings in the SecurityConfig class.

## Usage

   1. Register a new user account.
   2. View the user based on ID 
   3. Perform user management operations via available endpoints.
   4. Only users with ADMIN role can view all users and delete them.
   5. Users without credential can only create user(public endpoint). 

## API Documentation

API documentation WILL BE available on Swagger.

## Contributing

Not up for contribution for the moment. 

## License

$$$.

