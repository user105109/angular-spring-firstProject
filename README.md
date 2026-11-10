# Student Payment Management System

A full-stack application designed to manage student profiles and track academic payment statuses. The project is structured with a decoupled architecture, separating the backend business logic from the upcoming frontend user interface.

## 🚀 Current Architecture & Features (Backend)

The backend is built using **Spring Boot 3.x** and **Java 25**, adopting clean architecture principles and separation of concerns.

*   **Domain Model:** Manages `Student` records and associated `Payment` histories (tracking payment amounts, types, and statuses like `CREATED`, `VALIDATED`, or `REJECTED`).
*   **Database Seeding:** Features an automated `ApplicationDataInitializer` (`CommandLineRunner`) to bootstrap the in-memory database with randomized test records upon startup.
*   **Data Isolation (DTOs):** Implements modern Java `record` types as Data Transfer Objects (DTOs) to secure data layer boundaries and enforce strict API contracts.
*   **Persistence Layer:** Powered by **Spring Data JPA** connected to an in-memory **H2 Database**, allowing rapid prototyping and testing.
*   **API Documentation:** Integrated with **Springdoc OpenAPI (Swagger UI)** for interactive endpoint testing.

---

## 🛠️ Tech Stack

*   **Backend:** Java 25, Spring Boot, Spring Data JPA, Spring Web
*   **Database:** H2 (In-Memory)
*   **Utilities:** Lombok (Builder pattern, clean boilerplate management)

---

## 🔮 Upcoming Milestones: The Frontend-Backend Connection

The project is moving toward a decoupled, full-stack architecture. The next major milestone focuses entirely on **connecting, securing, and streaming data between Spring Boot and Angular**:

### 1. Cross-Origin Resource Sharing (CORS) Integration
Because the Angular client runs on its own local server (typically port `4200`) and the Spring Boot API runs on another (port `8083`), browsers block communications by default. We are setting up a global `@CrossOrigin("*")` policy and filter configuration on Spring Boot to securely authorize the Angular application to send requests and read response headers.

### 2. Secure Pipeline Bridge (HTTP Interceptors)
To prevent users from logging in on every click, we are establishing an automated link:
*   **The Request:** Angular will use an **HTTP Interceptor** to automatically inject credentials (via HTTP Basic or Bearer tokens) into the header of every single outgoing API request.
*   **The Guard:** On the backend, the `SecurityFilterChain` will intercept these requests, extract the headers, pass them to the `DaoAuthenticationProvider`, and grant or deny access dynamically.

### 3. API Contract Alignment via DTOs
The communication bridge relies on strict JSON object contracts. The backend `AccountRestController` will serve as the single source of truth, exchanging dedicated DTO data objects (like `CreateUserDTO` and `AppUserDTO`) with Angular's `HttpClient` services. This ensures that the frontend never receives sensitive database structures or raw hashes.

### 4. Dynamic Role-Based UI Rendering
The backend authorization state will directly dictate what the user sees in the browser. When Angular fetches a user's secure profile (`AppUserDTO`), it parses the `Set<String> roles` array. The frontend will then use structural directives to dynamically hide or show navigation links, management panels, and action buttons depending on whether the user is an `ADMIN` or a standard `USER`.

---

## ⚙️ Getting Started

### Prerequisites
*   Java Development Kit (JDK) 25
*   Apache Maven 3.x

### Database & Console Access
By default, the application runs on port `8083`.
*   **H2 Database Console:** Accessible at `http://localhost:8083/h2-console`
    *   **JDBC URL:** `jdbc:h2:mem:testdb`
    *   **User Name:** `sa`
    *   **Password:** *(Leave blank)*
*   **Swagger API Docs:** Accessible at `http://localhost:8083/swagger-ui/index.html`
