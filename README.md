# API E-mail

This project is a email sending API

## Requirements

- Java 17
- Spring Boot 3
- PostgreSQL 15

## How to use

You can send requests to the API using the available routes. The routes are:

- POST `/api/auth/register`
- POST `/api/auth/login`
- GET `/api/users/me`
- GET `/api/users` - Only ADMIN
- POST `/api/emails/send`

Some endpoints require authentication via a JWT access token.

## Swagger Documentation

The API documentation is available on Swagger. To access it, follow these steps:

1. Run project
2. Open a web browser and navigate to `http://localhost:8080/swagger-ui/index.html`
3. The API documentation should be displayed in Swagger

## How to run

1. Clone this repository
2. create a database named `db_api_email`
3. Configure your database in the application-dev.yml or application-prod.yml file
4. Configure your e-mail and password, Your email must be configured to be used on apps/systems, in the application-dev.yml or application-prod.yml file
5. Run project

## Contribution

Please feel free to send pull requests and report issues.
