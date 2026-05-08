# ABQ Desi Bites 🍛

> **Every Bite, A Memory**

A full-stack professional website for **ABQ Desi Bites** — an authentic South Indian & Hyderabadi food business located in Albuquerque, New Mexico.

---

## 🌐 Live Website

> Coming soon — hosted on Vercel (frontend) + Railway (backend)

---

## 📋 About the Project

ABQ Desi Bites serves authentic dosas, idly, Hyderabadi biryani, momos, and more. This website provides:

- A beautiful, responsive landing page with the full brand story
- A live menu page fetched from a real backend API and database
- An About Us page with business info and location
- A Contact Us form that saves inquiries to the database

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Frontend | Angular 21, TypeScript, SCSS |
| Backend | Java 17, Spring Boot 3.2 |
| Database | PostgreSQL 16 |
| ORM | Spring Data JPA / Hibernate |
| Migrations | Flyway |
| Build Tool | Maven 3.9 |
| Hosting (Frontend) | Vercel |
| Hosting (Backend) | Railway |
| Version Control | Git / GitHub |

---

## 📁 Project Structure

```
abq-desi-bites/
├── backend/                          # Spring Boot REST API
│   └── src/main/java/com/abqdesibites/
│       ├── controller/               # REST endpoints
│       ├── service/                  # Business logic
│       ├── repository/               # Database queries (JPA)
│       ├── model/                    # Database entities
│       ├── dto/                      # API request/response shapes
│       └── config/                   # CORS configuration
│   └── src/main/resources/
│       ├── application.yml           # App configuration
│       └── db/migration/             # Flyway SQL migrations
│           ├── V1__create_schema.sql # Table definitions
│           └── V2__seed_menu_data.sql# All menu items
│
└── frontend/                         # Angular application
    └── src/app/
        ├── core/services/            # HTTP services (menu, contact)
        ├── shared/                   # Navbar, Footer components
        └── pages/                    # Route-level components
            ├── home/                 # Landing page
            ├── menu/                 # Full menu (live from API)
            ├── about/                # About Us
            └── contact/              # Contact form
```

---

## 🚀 Running Locally

### Prerequisites
- Java 17+
- Maven 3.9+
- Node.js 20+
- Angular CLI 21+
- PostgreSQL 16

### 1. Database Setup
```bash
# Connect to PostgreSQL and create the database
psql -U postgres -c "CREATE DATABASE abqdesibites;"
```

### 2. Backend
```bash
cd backend
mvn spring-boot:run
```
Backend runs at `http://localhost:8080`

Flyway automatically creates tables and seeds all menu data on first run.

### 3. Frontend
```bash
cd frontend
npm install
ng serve
```
Frontend runs at `http://localhost:4200`

---

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/menu` | Returns all menu categories and items |
| POST | `/api/contact` | Saves a contact form inquiry |

---

## 🗄️ Database Schema

```sql
menu_categories   -- id, name, description, sort_order
menu_items        -- id, category_id, name, description, price, is_available, is_weekend, sort_order
contact_inquiries -- id, name, email, phone, message, submitted_at
```

---

## 🔒 Security Practices

- No hardcoded secrets — database password read from environment variable `DB_PASSWORD`
- Server-side input validation on all API endpoints (`@Valid`, Jakarta Bean Validation)
- CORS restricted to known origins only
- SQL injection protected via JPA parameterized queries

---

## 📍 Business Info

| | |
|---|---|
| Name | ABQ Desi Bites |
| Address | 1800 Lomas Blvd NE, Albuquerque, NM 87112 |
| Phone | +1 (505) 677-4394 |
| Email | abqdesibites@gmail.com |
| Instagram | [@abqdesibites](https://instagram.com/abqdesibites) |
| Ordering | Pickup & DoorDash |

---

## 👨‍💻 Developer

Built with ❤️ by [Raj Gopala Vamsee](https://github.com/RajVamsee)
