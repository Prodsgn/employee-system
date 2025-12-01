# Employee Management System

This project is a simple employee management system with a React/TypeScript frontend and a Spring Boot backend.

## Project Overview

The application is divided into two main parts:

*   **`employee-api`**: A Spring Boot application that provides a RESTful API for managing employee data. It uses Spring Data JPA with a SQLite database and is seeded with 100 random employees on startup.
*   **`employee-ui`**: A React/TypeScript application that provides a user interface for viewing the list of employees.

## Technologies Used

### Backend (`employee-api`)

*   Java 17
*   Spring Boot 2.7.5
*   Spring Data JPA
*   SQLite
*   Lombok
*   MapStruct
*   Java Faker (for data seeding)
*   Swagger (for API documentation)

### Frontend (`employee-ui`)

*   React
*   TypeScript
*   React Scripts

## Getting Started

To get the application up and running, you'll need to have the following installed:

*   Java 17
*   Maven
*   Node.js and npm

### 1. Run the Backend (`employee-api`)

1.  Navigate to the `employee-api` directory:
    ```bash
    cd employee-api
    ```
2.  Build the application using Maven:
    ```bash
    mvn clean install
    ```
3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```
    The API will be available at `http://localhost:8081`.

### 2. Run the Frontend (`employee-ui`)

1.  Navigate to the `employee-ui` directory:
    ```bash
    cd employee-ui
    ```
2.  Install the dependencies:
    ```bash
    npm install
    ```
3.  Start the development server:
    ```bash
    npm start
    ```
    The UI will be available at `http://localhost:3000`.
