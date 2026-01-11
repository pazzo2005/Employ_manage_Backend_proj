# TodoList API – Spring Boot & JWT Authentication

A secure RESTful Todo List backend built using Spring Boot, Spring Security, JWT authentication, and PostgreSQL.

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- PostgreSQL
- Maven

## Features

- User authentication using JWT
- Role-based authorization (ADMIN / USER)
- Secure login endpoint
- CRUD operations for Todos
- Stateless authentication


## Project Structure

src/main/java  
├── controller  
├── security  
├── service  
├── repository  
├── dto  
└── model



## Environment Setup

### Prerequisites
- Java 17+
- Maven
- PostgreSQL
- Postman (for API testing)


## Common Issues

- 403 Forbidden → Ensure `/api/auth/**` is permitted in Spring Security
- Invalid token → Check Authorization header format
