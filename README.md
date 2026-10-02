# Blog API

A Spring Boot backend for a blog platform where users can register, log in, create blog posts, search for posts, and interact with comments. The project is built using Java 17, Spring Boot 3, Spring Security, JWT authentication, Spring Data JPA, and PostgreSQL.

## Project Overview

This application provides the backend services for a simple blogging system. It supports:

- User registration and login
- JWT-based authentication and authorization
- Post creation, retrieval, update, deletion, and search
- Comment creation, retrieval, update, and deletion
- Tag-based categorization for posts
- Role-based access control for post operations
- Database persistence using PostgreSQL
- Swagger/OpenAPI documentation

## Tech Stack

- Java 17
- Spring Boot 3.5.15
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- JWT (jjwt)
- Lombok
- Validation
- SpringDoc OpenAPI

## Core Features

### Authentication

The API exposes authentication endpoints for registering users and logging in:

- `POST /auth/register`
- `POST /auth/login`

On successful login, the server returns a JWT token that is used for protected routes.

### Blog Posts

Authenticated users can create posts, and public users can view and search them:

- `POST /api/posts/create`
- `GET /api/posts`
- `GET /api/posts/{id}`
- `PUT /api/posts/update/{id}`
- `DELETE /api/posts/delete/{id}`
- `GET /api/posts/search?keyword={value}`

Post ownership checks are enforced by custom security rules so only the author or an admin may update or delete a post.

### Comments

Users can comment on posts and manage their comments:

- `POST /api/comment/create/{postId}`
- `GET /api/comment/{postId}`
- `PUT /api/comment/{commentId}`
- `DELETE /api/comment/{commentId}`

### Tags

Posts can be associated with multiple tags. Tag IDs are passed in the post creation request and resolved from the database before saving the post.

## Main Project Structure

```text
src/
├── main/
│   ├── java/com/shobhit/blog_api/
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   └── SecurityBeans.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── PostController.java
│   │   │   └── CommentController.java
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   └── response/
│   │   ├── entity/
│   │   │   ├── User.java
│   │   │   ├── Post.java
│   │   │   ├── Comment.java
│   │   │   ├── Tag.java
│   │   │   └── Role.java
│   │   ├── exception/
│   │   ├── repository/
│   │   ├── security/
│   │   ├── service/
│   │   └── BlogApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/shobhit/blog_api/
```

## Data Model

The application is centered around a few entities:

- `User`: stores username, email, password, and role
- `Post`: stores title, content, created date, author, and tags
- `Comment`: stores comment content, parent post, author, and timestamps
- `Tag`: stores tag values used to categorize posts

## Security Configuration

The backend uses stateless JWT-based authentication.

- CSRF is disabled.
- Public endpoints are limited to `/auth/**`, `/swagger-ui/**`, and `/v3/api-docs/**`.
- All other requests require authentication.
- JWT filter is added before the Spring Security username/password filter.

## Database Configuration

The project is configured to use PostgreSQL with the following default settings:

- Database: `blogdb`
- Username: `postgres`
- Host: `localhost`
- Port: `5432`
- Schema auto-update: enabled via `spring.jpa.hibernate.ddl-auto=update`

Update the values in `src/main/resources/application.properties` to match your local or cloud PostgreSQL setup.

## Swagger / API Docs

The project includes OpenAPI support via SpringDoc. Once the application is running, documentation is available at:

- `http://localhost:8081/swagger-ui/index.html`
- `http://localhost:8081/v3/api-docs`

## Run the Project

### Prerequisites

- Java 17 or later
- Maven
- PostgreSQL installed and running

### Build and run

```bash
./mvnw clean install
./mvnw spring-boot:run
```

or on Windows:

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

The application runs by default on port `8081`.

## Example Workflow

1. Register a user using `POST /auth/register`
2. Log in with `POST /auth/login` to receive a JWT token
3. Pass the token in the `Authorization` header as a Bearer token
4. Create posts using `POST /api/posts/create`
5. Add comments to posts using `POST /api/comment/create/{postId}`
6. Search or retrieve posts through the public endpoints

## Notes

This project is a backend-only blog application and does not include a frontend. It is suitable as a base for a full-stack blog platform, a portfolio API, or a learning project demonstrating Spring Boot, JPA, JWT security, and REST API design.

## License

This project currently does not specify a license in the Maven configuration, so it is best treated as a personal or educational codebase unless a license is added later.
