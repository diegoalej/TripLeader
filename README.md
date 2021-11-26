# SandBar Project
This application is intended to be used as a tool to plan single- and multi-day outings in the outdoors or any other occasion with a focus on logistics, meals, and expenses.

## Project Description
The SandBar project makes use of the Spring framework and MySQL for the back-end REST API. For the front end an Angular app makes calls to the Spring API and handles user views. 

Use cases include:
* User can create new account from landing page
* User can safely log in to account from landing page
* User can see and edit personal information (trips, expenses, meals)
* User can see and edit other users if member of admin group

### MySQL DB and JPA Project 
Once the DB schema was established, JPASandBar was created as a Gradle project to model and communicate with the DB. The project incorporates JPA (Hibernate) and JDBC driver (MySQL) to connect with the DB, and uses JUnit testing to ensure data and relationships are accurate. This project is meant to be a dependency of the main Spring Boot project on which a REST API will serve the data modeled in this JPA project. 

### Spring Boot Project
The confluence project brings in JPASandbar as a dependency and sets up the API endpoints that, using services and repositories, operate CRUD functions on the required entities to serve data or perform the required function. This project is secured using Spring Security Basic Auth. 

### Angular Project
This project displays all user views and performs all functions by making calls to the REST API and other API's as necessary. 