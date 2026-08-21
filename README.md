\# 🚀 LinkSphere - User Service



A production-style social media backend built with \*\*Spring Boot 4\*\*, \*\*PostgreSQL\*\*, \*\*Spring Security\*\*, and \*\*JWT Authentication\*\*.



> \*\*LinkSphere\*\* is an Instagram/LinkedIn-inspired social media platform built as a milestone-based full-stack project.



\---



\## 📌 Project Overview



This repository contains the \*\*User Service\*\* of LinkSphere.



Current features include:



\* User Registration

\* User Login

\* BCrypt Password Hashing

\* JWT Authentication

\* Protected User APIs

\* PostgreSQL Integration

\* Global Exception Handling



\---



\## 🛠️ Tech Stack



| Technology      | Version          |

| --------------- | ---------------- |

| Java            | 21               |

| Spring Boot     | 4.1.0            |

| Spring Security | 6.x              |

| PostgreSQL      | 17               |

| Hibernate / JPA | Latest           |

| JWT (jjwt)      | 0.12.7           |

| Maven           | Wrapper (`mvnw`) |



\---



\## 📁 Project Structure



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



\---



\## ⚙️ Configuration



\### PostgreSQL Database



Create a database named:



```sql

CREATE DATABASE linksphere\_users;

```



\### Environment Variable



Set your PostgreSQL password before running the application.



\*\*PowerShell\*\*



```powershell

$env:DB\_PASSWORD="YOUR\_POSTGRES\_PASSWORD"

```



\---



\## ▶️ Run the Project



```powershell

cd C:\\Users\\rahul\\LinkSphere\\user-service



$env:DB\_PASSWORD="YOUR\_POSTGRES\_PASSWORD"



.\\mvnw.cmd spring-boot:run

```



Server starts on:



```text

http://localhost:8081

```



\---



\## 🔑 Authentication Flow



\### Register User



\*\*POST\*\* `/api/auth/register`



```json

{

&#x20; "username": "rahul999",

&#x20; "email": "rahul999@example.com",

&#x20; "password": "SecurePass123",

&#x20; "fullName": "Rahul Negi",

&#x20; "bio": "Registered through Auth API"

}

```



\### Login User



\*\*POST\*\* `/api/auth/login`



```json

{

&#x20; "email": "rahul999@example.com",

&#x20; "password": "SecurePass123"

}

```



\*\*Response\*\*



```json

{

&#x20; "id": 3,

&#x20; "username": "rahul999",

&#x20; "email": "rahul999@example.com",

&#x20; "token": "<JWT\_TOKEN>",

&#x20; "message": "Login successful!"

}

```



\---



\## 🔒 Protected API Example



\### Get User Profile



\*\*GET\*\* `/api/users/2`



Without token:



```text

401 Unauthorized

```



With JWT:



```http

Authorization: Bearer <JWT\_TOKEN>

```



Returns:



```json

{

&#x20; "id": 2,

&#x20; "username": "rahul789",

&#x20; "email": "rahul789@example.com",

&#x20; "fullName": "Rahul Negi",

&#x20; "bio": "Testing BCrypt"

}

```



\---



\## 📊 Implemented Features



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

| JWT Protected APIs        | ✅      |

| Global Exception Handling | ✅      |



\---



\## 🧪 API Testing (PowerShell)



\### Register



```powershell

Invoke-RestMethod -Uri "http://localhost:8081/api/auth/register" `

\-Method POST `

\-ContentType "application/json" `

\-Body '{"username":"demo","email":"demo@example.com","password":"Password123","fullName":"Demo User","bio":"Hello LinkSphere"}'

```



\### Login



```powershell

Invoke-RestMethod -Uri "http://localhost:8081/api/auth/login" `

\-Method POST `

\-ContentType "application/json" `

\-Body '{"email":"demo@example.com","password":"Password123"}'

```



\---



\## 📈 Milestone Progress



\### ✅ Milestone 1 — User Service Foundation



\* Spring Boot Project Setup

\* PostgreSQL Connection

\* User Entity

\* Repository

\* Service Layer

\* REST Controller



\### ✅ Milestone 2 — Authentication Module



\* BCrypt Password Hashing

\* Register API

\* Login API

\* JWT Token Generation

\* JWT Authentication Filter

\* Global Exception Handling



\### ⏳ Upcoming Milestones



\* Profile Management

\* Follow / Unfollow

\* Posts Service

\* Feed API

\* Likes \& Comments

\* Stories

\* Chat (WebSocket)

\* Notifications

\* Search

\* Docker Deployment



\---



\## 💻 Git Milestones



```text

Milestone 1: User Service with PostgreSQL and Profile APIs

Milestone 2.1: BCrypt Password Hashing

Milestone 2.2: Register API

Milestone 2.3: Login API + Global Exception Handling

Milestone 2.4: JWT Token Generation

Milestone 2.5: JWT Authentication Filter

```



\---



\## 👨‍💻 Author



\*\*Rahul Negi\*\*



Building \*\*LinkSphere\*\* as a production-style social media backend using Spring Boot, PostgreSQL, JWT, and modern backend architecture.



