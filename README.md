# 🚀 LinkSphere - User Service

A production-style social media backend built with **Spring Boot 4**, **PostgreSQL**, **Spring Security**, and **JWT Authentication**.

> **LinkSphere** is an Instagram/LinkedIn-inspired social media platform built as a milestone-based full-stack project.

---

## 📌 Project Overview

This repository contains the **User Service** of LinkSphere.

### Current Features

* User Registration
* User Login
* BCrypt Password Hashing
* JWT Authentication
* Protected User APIs
* PostgreSQL Integration
* Global Exception Handling

---

## 🛠️ Tech Stack

| Technology      | Version                |
| --------------- | ---------------------- |
| Java            | 21                     |
| Spring Boot     | 4.1.0                  |
| Spring Security | 6.x                    |
| PostgreSQL      | 17                     |
| Hibernate / JPA | Latest                 |
| JWT (jjwt)      | 0.12.7                 |
| Maven           | Maven Wrapper (`mvnw`) |

---

## 📁 Project Structure

```text
user-service/
├── src/
│   ├── main/
│   │   ├── java/com/linksphere/user/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── UserServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## ⚙️ Configuration

### PostgreSQL Database

Create a database named:

```sql
CREATE DATABASE linksphere_users;
```

### Environment Variable (PowerShell)

Before running the application:

```powershell
$env:DB_PASSWORD="YOUR_POSTGRES_PASSWORD"
```

---

## ▶️ Run the Project

```powershell
cd C:\Users\rahul\LinkSphere\user-service

$env:DB_PASSWORD="YOUR_POSTGRES_PASSWORD"

.\mvnw.cmd spring-boot:run
```

Server starts at:

```text
http://localhost:8081
```

---

## 🔑 Authentication Flow

### Register User

**POST** `/api/auth/register`

```json
{
  "username": "rahul999",
  "email": "rahul999@example.com",
  "password": "SecurePass123",
  "fullName": "Rahul Negi",
  "bio": "Registered through Auth API"
}
```

### Login User

**POST** `/api/auth/login`

```json
{
  "email": "rahul999@example.com",
  "password": "SecurePass123"
}
```

### Login Response

```json
{
  "id": 3,
  "username": "rahul999",
  "email": "rahul999@example.com",
  "token": "<JWT_TOKEN>",
  "message": "Login successful!"
}
```

---

## 🔒 Protected API Example

### Get User Profile

**GET** `/api/users/2`

Without JWT:

```text
401 Unauthorized
```

With JWT:

```http
Authorization: Bearer <JWT_TOKEN>
```

Response:

```json
{
  "id": 2,
  "username": "rahul789",
  "email": "rahul789@example.com",
  "fullName": "Rahul Negi",
  "bio": "Testing BCrypt"
}
```

---

## 📊 Implemented Features

| Feature                   | Status |
| ------------------------- | ------ |
| PostgreSQL Integration    | ✅      |
| JPA/Hibernate Entity      | ✅      |
| User Repository           | ✅      |
| Create User API           | ✅      |
| Get User API              | ✅      |
| BCrypt Password Hashing   | ✅      |
| Register API              | ✅      |
| Login API                 | ✅      |
| JWT Token Generation      | ✅      |
| JWT Authentication Filter | ✅      |
| JWT Protected APIs        | ✅      |
| Global Exception Handling | ✅      |

---

## 🧪 API Testing (PowerShell)

### Register

```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/auth/register" `
  -Method POST `
  -ContentType "application/json" `
  -Body '{"username":"demo","email":"demo@example.com","password":"Password123","fullName":"Demo User","bio":"Hello LinkSphere"}'
```

### Login

```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/auth/login" `
  -Method POST `
  -ContentType "application/json" `
  -Body '{"email":"demo@example.com","password":"Password123"}'
```

---

## 📈 Milestone Progress

### ✅ Milestone 1 — User Service Foundation

* Spring Boot Project Setup
* PostgreSQL Connection
* User Entity
* Repository
* Service Layer
* REST Controller

### ✅ Milestone 2 — Authentication Module

* BCrypt Password Hashing
* Register API
* Login API
* JWT Token Generation
* JWT Authentication Filter
* Global Exception Handling

### ⏳ Upcoming Milestones

* Profile Management
* Follow / Unfollow System
* Posts Service
* Feed API
* Likes & Comments
* Stories
* Chat (WebSocket)
* Notifications
* Search
* Docker Deployment

---

## 💻 Git Milestones

```text
Milestone 1: User Service with PostgreSQL and Profile APIs
Milestone 2 Step 2.1: BCrypt password hashing implemented
Milestone 2 Step 2.2: Register API implemented
Milestone 2 Step 2.3: Login API and global exception handling
Milestone 2 Step 2.4: JWT token generation implemented
Milestone 2 Step 2.5: JWT authentication filter implemented
```

Current Git History:

```text
4e587d4 docs: add project README for LinkSphere user-service
34715f1 Milestone 2 Step 2.5: JWT authentication filter implemented
7d4c42e Milestone 2 Step 2.4: JWT token generation implemented
40e725b Milestone 2 Step 2.3: Login API and global exception handling
a9fe1a1 Milestone 2 Step 2.2: Register API implemented
7700470 Milestone 2 Step 2.1: BCrypt password hashing implemented
233589a Milestone 1: User Service with PostgreSQL and Profile APIs
```

---

## 👨‍💻 Author

**Rahul Negi**

Building **LinkSphere**, a production-style social media backend using Spring Boot, PostgreSQL, JWT, and modern backend architecture.

---

## 🚀 Project Status

**Backend Progress:** Milestone 1 ✅ | Milestone 2 ✅

Next milestone: **Profile Management (`/api/users/me`, Update Profile, Profile Picture Upload)**.
