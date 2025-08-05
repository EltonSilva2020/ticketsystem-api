# 🎟️ TicketSystem API

A backend-focused ticket management system built with **Java (Spring Boot)** and **MySQL**, designed to support future integration with a modern Angular frontend. The API provides robust endpoints for managing support tickets, users, departments, and categories.

---

## 🛠️ Technologies Used

**Backend:**
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- MapStruct (DTO Mapping)
- RESTful API

**Frontend:**
- Angular *(in development)*
- Node.js
- HTML & SCSS *(planned)*

---

## 📄 API Documentation

The API is documented using **Swagger**. Once the backend is running, access:http://localhost:8080/swagger-ui.html

---

## 🔑 Key Endpoints

| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| GET    | `/api/tickets`       | List all tickets         |
| POST   | `/api/tickets`       | Create a new ticket      |
| GET    | `/api/tickets/{id}`  | Get ticket by ID         |
| PUT    | `/api/tickets/{id}`  | Update a ticket          |
| DELETE | `/api/tickets/{id}`  | Delete a ticket          |

---

## 🚀 Running Locally

### Backend

```bash
cd ticketsystem-api
./mvnw spring-boot:run
----
🎯 Features

•  Ticket CRUD operations
•  User and department management
•  DTO mapping with MapStruct
•  Swagger API documentation
•  Ready for frontend integration
