## DACHSER Technical Assessment - Profit Calculation System

This project is a technical challenge for a Senior Software Engineer position at DACHSER. The goal is to implement a backend and optionally a frontend system to calculate profit or loss per logistics shipment.

### 📌 Project Goals

- Track incomes and costs related to logistics shipments
- Calculate and store the profit or loss of each shipment

### 🛠️ Tech Stack

#### Backend:
- Java 17
- Spring Boot 3.1.5
- Maven 3.9.3
- H2 in-memory database
- RESTful API design

#### Frontend (optional):
- Angular 17.1.0
- Angular Material 17.3.1
- Bootstrap 5.3.3
- Node 21

### 📊 Data Model Overview

The system includes the following core entities:
- `shipment`: shipment metadata and status
- `cost`: costs associated with a shipment
- `income`: income received for a shipment
- `profit_loss`: calculated result with historical tracking

### 🔐 Users & Permissions

The system includes authentication and role-based access control using JWT tokens.

#### Predefined Users

| Username | Password | Role        | Permissions                              |
|----------|----------|-------------|------------------------------------------|
| manager  | password | ROLE_MANAGER| Can **create**, **edit**, **delete**, and **view** shipments and related data |
| user     | password | ROLE_USER   | Can **only view** shipments and profit/loss information |

You can log in using the `/api/v1/auth/login` endpoint to receive a JWT token.

> ⚠️ Users with `ROLE_USER` will not see options for creating or deleting shipments in the UI.

---

### 🚀 Running the Frontend

To run the Angular frontend locally:

```bash
cd frontend
npm install
ng serve
