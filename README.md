# 🚀 LinkSphere — User Service

> **A production-style social networking backend built with Spring Boot, PostgreSQL, Spring Security, and JWT-based authentication.**

LinkSphere is an **Instagram/LinkedIn-inspired social media platform** being developed with a milestone-driven backend architecture.

The project focuses on building a **secure, scalable, and maintainable backend** with authentication, user management, stories, social features, event-driven communication, caching, and containerized deployment.

---

## 📌 Overview

The **User Service** is one of the core backend services of LinkSphere.

It is responsible for:

* User registration and authentication
* Secure password management
* JWT-based authorization
* User profile management
* Protected REST APIs
* PostgreSQL persistence
* Story management
* Global exception handling

The service is being developed incrementally as part of the LinkSphere backend architecture.

---

## ✨ Current Capabilities

### 🔐 Authentication & Security

* User registration
* User login
* BCrypt password hashing
* JWT token generation
* JWT authentication filter
* Protected REST endpoints
* Authentication-based API access
* Global exception handling

### 👤 User Management

* User entity
* User repository
* User creation
* User profile retrieval
* PostgreSQL persistence

### 📖 Stories — In Development

* Story entity
* User-to-story relationship
* Story content
* Story creation timestamp
* Story expiration timestamp
* Story repository

> **Story Controller and Service APIs are currently under development.**

---

# 🛠️ Technology Stack

| Technology         | Version / Usage |
| ------------------ | --------------- |
| ☕ Java             | 21              |
| 🌱 Spring Boot     | 4.x             |
| 🔐 Spring Security | 6.x             |
| 🗄️ PostgreSQL     | 17              |
| 🧩 Hibernate / JPA | Latest          |
| 🔑 JWT / JJWT      | 0.12.7          |
| 📦 Maven           | Maven Wrapper   |
| 🔧 Git             | Version Control |

---

# 🏗️ Architecture

The project follows a layered backend architecture:

```text
                    ┌─────────────────────┐
                    │      Client         │
                    │ Web / Mobile / API  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   REST Controllers  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    Service Layer    │
                    │ Business Logic      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │ Repository Layer    │
                    │ Spring Data JPA     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     PostgreSQL      │
                    └─────────────────────┘
```

Security is handled through:

```text
Client
  │
  ▼
JWT Token
  │
  ▼
JWT Authentication Filter
  │
  ▼
Spring Security
  │
  ▼
Protected Controller
```

---

# 📁 Project Structure

```text
user-service/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── linksphere/
│   │   │           └── user/
│   │   │               ├── config/
│   │   │               ├── controller/
│   │   │               ├── dto/
│   │   │               ├── entity/
│   │   │               │   ├── User.java
│   │   │               │   └── Story.java
│   │   │               ├── exception/
│   │   │               ├── repository/
│   │   │               │   ├── UserRepository.java
│   │   │               │   └── StoryRepository.java
│   │   │               ├── security/
│   │   │               ├── service/
│   │   │               └── UserServiceApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# 🗄️ Database Configuration

LinkSphere currently uses **PostgreSQL** for persistent data storage.

### Create Database

```sql
CREATE DATABASE linksphere_users;
```

### Configure Database Password

PowerShell:

```powershell
$env:DB_PASSWORD="YOUR_POSTGRES_PASSWORD"
```

> Database credentials should be provided through environment variables rather than committed directly to source control.

---

# ▶️ Running the Service

Clone the repository and navigate to the service:

```powershell
cd C:\Users\rahul\LinkSphere\user-service
```

Set the PostgreSQL password:

```powershell
$env:DB_PASSWORD="YOUR_POSTGRES_PASSWORD"
```

Start the application:

```powershell
.\mvnw.cmd spring-boot:run
```

The service runs on:

```text
http://localhost:8081
```

---

# 🔐 Authentication

LinkSphere uses **JWT-based authentication**.

### Authentication Flow

```text
Register
   │
   ▼
User Credentials
   │
   ▼
BCrypt Password Hashing
   │
   ▼
PostgreSQL
```

Login:

```text
Login Request
     │
     ▼
Validate Credentials
     │
     ▼
Generate JWT
     │
     ▼
Return Token
```

Protected request:

```text
Client
  │
  │ Authorization: Bearer <JWT>
  ▼
JWT Filter
  │
  ▼
Spring Security
  │
  ▼
Protected API
```

---

# 🔑 API Endpoints

## Authentication

### Register

```http
POST /api/auth/register
```

Example:

```json
{
  "username": "demo",
  "email": "demo@example.com",
  "password": "Password123",
  "fullName": "Demo User",
  "bio": "Hello LinkSphere"
}
```

### Login

```http
POST /api/auth/login
```

Example:

```json
{
  "email": "demo@example.com",
  "password": "Password123"
}
```

Example response:

```json
{
  "id": 3,
  "username": "demo",
  "email": "demo@example.com",
  "token": "<JWT_TOKEN>",
  "message": "Login successful!"
}
```

---

# 👤 User API

### Get User Profile

```http
GET /api/users/{id}
```

Authentication:

```http
Authorization: Bearer <JWT_TOKEN>
```

Without authentication:

```text
401 Unauthorized
```

Example response:

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

# 📖 Stories

Stories are currently being developed under **Milestone 7**.

### Story Model

Each story contains:

```text
Story
├── id
├── user
├── content
├── createdAt
└── expiresAt
```

### Story Development Progress

| Component                 | Status         |
| ------------------------- | -------------- |
| Story Entity              | 🟡 In Progress |
| User → Story Relationship | 🟡 In Progress |
| Story Repository          | 🟡 In Progress |
| Story Create API          | ⏳ Planned      |
| Story Fetch API           | ⏳ Planned      |
| Story Expiration          | ⏳ Planned      |
| Story Testing             | ⏳ Planned      |

---

# 📊 Feature Status

| Feature                   | Status |
| ------------------------- | :----: |
| Spring Boot Setup         |    ✅   |
| PostgreSQL Integration    |    ✅   |
| JPA / Hibernate           |    ✅   |
| User Entity               |    ✅   |
| User Repository           |    ✅   |
| User APIs                 |    ✅   |
| BCrypt Password Hashing   |    ✅   |
| Registration API          |    ✅   |
| Login API                 |    ✅   |
| JWT Generation            |    ✅   |
| JWT Authentication Filter |    ✅   |
| Protected APIs            |    ✅   |
| Global Exception Handling |    ✅   |
| Story Entity              |   🟡   |
| Story Repository          |   🟡   |
| Story Create API          |    ⏳   |
| Story Fetch API           |    ⏳   |

**Legend:**
✅ Completed · 🟡 In Progress · ⏳ Planned

---

# 🧪 API Testing

APIs can be tested using **PowerShell**, Postman, or any REST client.

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

# 📈 Development Roadmap

## ✅ Milestone 1 — User Service Foundation

* Spring Boot project setup
* PostgreSQL integration
* User entity
* Repository layer
* Service layer
* REST controllers

## ✅ Milestone 2 — Authentication

* BCrypt password hashing
* Registration API
* Login API
* JWT generation
* JWT authentication filter
* Global exception handling

## 🟡 Milestone 7 — Stories

### Step 7.1 — Story Entity & Repository

* Story entity
* User relationship
* Story content
* Creation timestamp
* Expiration timestamp
* Story repository

### Step 7.2 — Story Create API

* Story DTO
* Story service
* Story controller
* JWT authenticated story creation

### Step 7.3 — Story Fetch API

* Fetch active stories
* User-specific stories
* Expiration filtering

### Step 7.4 — Story Expiration

* Expiration handling
* Expired story filtering
* Cleanup strategy

### Step 7.5 — Story Testing

* API testing
* Repository testing
* Authentication testing
* Expiration testing

---

# 🚀 Future Roadmap

After Stories, the planned backend roadmap includes:

```text
Stories
   │
   ▼
Posts & Feed
   │
   ▼
Likes & Comments
   │
   ▼
Follow / Unfollow
   │
   ▼
Notifications
   │
   ▼
Chat / WebSocket
   │
   ▼
Search
   │
   ▼
Redis Caching
   │
   ▼
Kafka Event Streaming
   │
   ▼
API Gateway
   │
   ▼
Docker
   │
   ▼
Cloud Deployment
```

---

# 🧩 Planned Infrastructure

The long-term architecture is planned to incorporate:

* **Docker** — Containerization
* **Redis** — Caching and performance
* **Apache Kafka** — Event-driven communication
* **API Gateway** — Centralized routing
* **WebSocket** — Real-time messaging
* **AWS / Cloud** — Deployment and infrastructure

---

# 💻 Git Milestones

```text
Milestone 1: User Service with PostgreSQL and Profile APIs

Milestone 2 Step 2.1:
BCrypt password hashing implemented

Milestone 2 Step 2.2:
Register API implemented

Milestone 2 Step 2.3:
Login API and global exception handling

Milestone 2 Step 2.4:
JWT token generation implemented

Milestone 2 Step 2.5:
JWT authentication filter implemented

Milestone 7 Step 7.1:
Story Entity + Repository

Milestone 7 Step 7.2:
Story Create API

Milestone 7 Step 7.3:
Story Fetch API

Milestone 7 Step 7.4:
Story Expiry / Cleanup

Milestone 7 Step 7.5:
Story Testing
```

---

# 📌 Current Development Status

```text
Milestone 1  ✅ Complete
Milestone 2  ✅ Complete
Milestone 7  🟡 In Progress
```

### Current Step

**Milestone 7 → Step 7.1 — Story Entity + Repository**

### Next Step

**Milestone 7 → Step 7.2 — Story Create API**

---

# 👨‍💻 Author

## Rahul Negi

Building **LinkSphere** — a production-style social media backend focused on secure authentication, scalable backend architecture, REST APIs, event-driven systems, caching, and cloud-ready infrastructure.

---

# ⭐ Project Vision

The goal of LinkSphere is to evolve from a basic social-media backend into a **production-style distributed backend system**.

The project emphasizes:

* 🔐 Secure authentication
* 🏗️ Clean backend architecture
* 🗄️ Reliable data persistence
* 🔄 RESTful API design
* ⚡ High-performance caching
* 📨 Event-driven architecture
* 💬 Real-time communication
* 🐳 Containerization
* ☁️ Cloud deployment
* 📈 Scalable microservices architecture

---

> **Status:** 🚧 Actively under development
> **Current milestone:** Milestone 7 — Stories
> **Current step:** Step 7.1 — Story Entity + Repository
