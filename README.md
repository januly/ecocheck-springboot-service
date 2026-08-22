# EcoCheck Spring Boot Service

This is the backend API for the EcoCheck climate action tracking system.

## What This Has

- User signup and login
- JWT token authentication
- Users, categories, climate actions, user actions and goals
- MySQL database
- Simple role support with USER, ADMIN and MANAGER

## Needed Before Running

- Java 25 or newer
- Maven
- MySQL running in the laptop

The app is using this database:

```text
ecocheck_db
```

The current database user in `application.properties` is:

```text
username: root
password:
```

## Database

If the database is not created, run this first:

```bash
mysql -uroot < database/create-database.sql
```

The tables and sample data are inside:

```text
src/main/resources/db/schema.sql
src/main/resources/db/data.sql
```

Spring Boot runs those files when the application starts.

## Start Backend

Run this from the backend folder:

```bash
mvn spring-boot:run
```

Backend URL:

```text
http://localhost:8080/ecocheck
```

## Test Login Details

Admin account:

```text
email: admin@ecocheck.lk
password: password123
role: ADMIN
```

User account:

```text
email: john@gmail.com
password: password123
role: USER
```

## Quick Test

Login API:

```bash
curl -X POST http://localhost:8080/ecocheck/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@ecocheck.lk","password":"password123"}'
```

It should return an access token.
