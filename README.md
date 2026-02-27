# 📬 Notify — Real-Time Notification Service

> A Spring Boot–powered backend notification service with a lightweight web frontend, designed for real-time delivery and scalable architecture.

---

## 🚀 Overview

**Notify** is a full-stack notification service built with **Java** and **Spring Boot**. It provides a clean RESTful API layer and a simple HTML-based frontend to trigger, manage, and deliver notifications. The project demonstrates backend engineering fundamentals — from application architecture to HTTP communication — in a practical, real-world use case.

---

## 🛠️ Tech Stack

| Layer       | Technology                        |
|-------------|-----------------------------------|
| Language    | Java                              |
| Framework   | Spring Boot                       |
| Build Tool  | Maven                             |
| Frontend    | HTML / CSS                        |
| Architecture| REST API                          |

---

## ✨ Features

- 🔔 **Notification API** — Create and manage notifications via RESTful endpoints
- 🌐 **Web Interface** — Minimal HTML frontend to interact with the notification service
- ⚙️ **Spring Boot Autoconfig** — Fast startup with embedded server (no external deployment needed)
- 📦 **Maven Build** — Reproducible builds with dependency management via `pom.xml`
- 🔌 **Extensible Design** — Easily extendable to support email, SMS, or push notification channels

---

## 📁 Project Structure

```
notify/
├── src/
│   ├── main/
│   │   ├── java/          # Spring Boot application, controllers, services, models
│   │   └── resources/     # Application config (application.properties) + HTML templates
│   └── test/
├── .mvn/wrapper/          # Maven wrapper for portable builds
├── pom.xml                # Project dependencies & build config
└── mvnw / mvnw.cmd        # Maven wrapper scripts (Linux/Windows)
```

---

## ⚡ Getting Started

### Prerequisites

- Java 17+ (or compatible version)
- Maven 3.6+ (or use the included wrapper)
- SendGrid API Key
- MongoDB URI

### Run Locally

```bash
# Clone the repository
git clone https://github.com/manojk-sai/notify.git
cd notify

# Update properties
Update the MongoDB URI, from email address, and sendgrid api key in the application.properties file

# Build & run using Maven wrapper
./mvnw spring-boot:run
```

The application will start at `http://localhost:8080`.

### Build JAR

```bash
./mvnw clean package
java -jar target/notify-*.jar
```

---

## 🔌 API Endpoints

| Method | Endpoint            | Description                    |
|--------|---------------------|--------------------------------|
| POST   | `/user`             | Create a user                  |
| POST   | `/notifications`    | Create a new notification      |

---

## 🧠 Key Concepts Demonstrated

- **RESTful API design** following standard HTTP conventions
- **Service-layer separation** for clean, testable business logic
- **Maven dependency management** and project lifecycle
- **Embedded Tomcat server** for self-contained deployment

---

## 🔮 Potential Enhancements

- [ ] WebSocket support for real-time push to frontend
- [ ] Email/SMS delivery via JavaMailSender or Twilio
- [ ] Database persistence with Spring Data JPA + MySQL/PostgreSQL
- [ ] User authentication with Spring Security + JWT
- [ ] Docker containerization
- [ ] CI/CD pipeline with GitHub Actions

---

## 👨‍💻 Author

**Manoj K Sai**  
[![GitHub](https://img.shields.io/badge/GitHub-manojk--sai-181717?logo=github)](https://github.com/manojk-sai)

---
