# SkillForge

SkillForge is a microservices-based learning management platform designed to provide a scalable and reliable environment for online learning. The platform allows users to create accounts, explore courses, enroll in courses, and manage learning activities.

The project is built using Spring Boot and follows a microservices architecture with independent services for authentication, user management, courses, enrollments, and payments.

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Cloud
- Spring Security
- Spring Data JPA
- Maven

### Infrastructure
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- PostgreSQL
- Redis
- Apache Kafka
- Docker

### Frontend
- React
- TypeScript

### Testing
- JUnit
- Mockito
- Postman

## Architecture

SkillForge follows a microservices architecture where each service is responsible for a specific business capability.

```text
                    Client
                      |
                      v
                API Gateway
                      |
          +-----------+-----------+
          |           |           |
          v           v           v
      Auth Service  User Service  Course Service
          |           |           |
          +-----------+-----------+
                      |
              Eureka Discovery
                      |
          +-----------+-----------+
          |                       |
          v                       v
   Enrollment Service       Payment Service
          |
          v
    Saga Orchestrator
```

## Microservices

| Service | Responsibility |
|---|---|
| API Gateway | Central entry point for client requests |
| Discovery Server | Service registration and discovery |
| Auth Service | Authentication, registration, and JWT |
| User Service | User profiles and account information |
| Course Service | Course creation and management |
| Enrollment Service | Student course enrollment |
| Payment Service | Payment processing |
| Saga Orchestrator | Coordination of distributed transactions |

## Project Structure

```text
SkillForge/
├── backend/
│   ├── api-gateway/
│   ├── discovery-server/
│   ├── auth-service/
│   ├── user-service/
│   ├── course-service/
│   ├── enrollment-service/
│   ├── payment-service/
│   └── saga-orchestrator/
│
├── frontend/
│   └── skillforge-web/
│
├── infrastructure/
│   ├── docker/
│   ├── postgres/
│   ├── redis/
│   ├── kafka/
│   └── monitoring/
│
├── docs/
│   ├── architecture/
│   ├── api/
│   └── diagrams/
│
├── scripts/
├── .env.example
├── .gitignore
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Key Features

- Secure user authentication and authorization
- User profile management
- Course creation and management
- Course enrollment
- Payment integration
- Service discovery and centralized routing
- Distributed transaction management using the Saga pattern
- Event-driven communication between services
- Containerized development environment

## Getting Started

### Prerequisites

- Java 25
- Maven
- Docker
- Git
- Node.js and pnpm

### Clone the Repository

```bash
git clone https://github.com/Pasindujeewan/SkillForge.git

cd SkillForge
```

### Environment Configuration

Create a `.env` file using the provided example:

```bash
cp .env.example .env
```

Configure the required environment variables before starting the application.

### Run the Backend

Each microservice can be started independently during development.

```bash
cd backend/discovery-server

./mvnw spring-boot:run
```

For Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Development Roadmap

- [x] Initialize project structure
- [x] Configure Discovery Server
- [ ] Configure API Gateway
- [ ] Implement User Service
- [ ] Implement Auth Service
- [ ] Implement Saga Orchestrator
- [ ] Implement Course Service
- [ ] Implement Enrollment Service
- [ ] Implement Payment Service
- [ ] Implement frontend
- [ ] Dockerize microservices
- [ ] Add automated testing
- [ ] Add monitoring and observability

## Project Goals

- Apply real-world microservices architecture principles
- Implement secure and scalable backend services
- Explore distributed transaction management
- Practice event-driven communication
- Improve software design, testing, and deployment skills

## License

This project is developed for educational and portfolio purposes.
