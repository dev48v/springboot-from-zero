# Spring Boot From Zero - Step-by-Step Guide

Learn Spring Boot by building a **Product CRUD REST API** from scratch.

## What You'll Build

A REST API that can:
- **GET** all products from the database
- **GET** a single product by ID
- **POST** (create) a new product
- **DELETE** a product

## Prerequisites

Before you start, make sure you have:

1. **JDK 17** — [Download OpenJDK 17](https://adoptium.net/)
   - After installing, verify: `java -version` → should show `17.x`
   - Set `JAVA_HOME` environment variable to your JDK path

2. **IntelliJ IDEA** — [Download Community Edition](https://www.jetbrains.com/idea/download/) (free)
   - Install the **Lombok plugin**: File → Settings → Plugins → search "Lombok" → Install
   - Enable annotation processing: File → Settings → Build → Compiler → Annotation Processors → ✅ Enable

3. **MySQL** — Running on localhost:3306
   - Create the database:
     ```sql
     CREATE DATABASE demo_db;
     ```

4. **Maven** — Comes bundled with IntelliJ (no separate install needed)

## How to Follow This Tutorial

Each commit in this repository is one step. Run `git log --oneline` to see all steps:

```
Step 8 - Exception handling & seed data
Step 7 - Controller: REST endpoints for Product CRUD
Step 6 - ServiceImpl: business logic + entity-DTO mapping
Step 5 - Service: define ProductService interface
Step 4 - DTO: create ProductDTO
Step 3 - Repository: ProductRepository with Spring Data JPA
Step 2 - Entity: Product domain model with JPA
Step 1 - Project setup: Spring Boot skeleton with Maven & MySQL
```

To see what changed in each step:
```bash
git log --oneline           # see all steps
git show <commit-hash>      # see what files changed in that step
git diff <hash1> <hash2>    # compare two steps
```

## How to Run

```bash
mvn spring-boot:run
```

App starts at: `http://localhost:8080`

## Test the API

Once the app is running, test these endpoints using **curl** or **Postman**:

### Get all products
```bash
curl http://localhost:8080/api/products
```
Response: JSON array of all products (3 seed products from data.sql)

### Get product by ID
```bash
curl http://localhost:8080/api/products/1
```
Response: Single product JSON object

### Create a new product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Galaxy S24","description":"Samsung flagship phone","price":899.99}'
```
Response: HTTP 201 + created product with generated ID

### Delete a product
```bash
curl -X DELETE http://localhost:8080/api/products/1
```
Response: HTTP 204 No Content (success, no body)

### Get a product that doesn't exist (error handling)
```bash
curl http://localhost:8080/api/products/999
```
Response: HTTP 404 + `{"status":404, "error":"Not Found", "message":"Product not found with id 999"}`

## Project Structure

```
src/main/java/com/example/springbootfromzero/
├── SpringbootFromZeroApplication.java   ← Entry point (Step 1)
├── domain/
│   └── Product.java                     ← JPA Entity - maps to DB table (Step 2)
├── repository/
│   └── ProductRepository.java           ← Database access layer (Step 3)
├── dto/
│   └── ProductDTO.java                  ← API response shape (Step 4)
├── service/
│   ├── ProductService.java              ← Business logic interface (Step 5)
│   └── impl/
│       └── ProductServiceImpl.java      ← Business logic implementation (Step 6)
├── controller/
│   └── ProductController.java           ← REST endpoints (Step 7)
└── exception/
    ├── ResourceNotFoundException.java   ← Custom 404 exception (Step 6)
    └── GlobalExceptionHandler.java      ← Global error handling (Step 8)
```

## Architecture (Request Flow)

```
Client (Browser/Postman/curl)
    ↓ HTTP Request (GET /api/products)
Controller (ProductController)
    ↓ calls service method
Service (ProductServiceImpl)
    ↓ calls repository method
Repository (ProductRepository)
    ↓ JPA generates SQL
Database (MySQL)
    ↑ returns rows
Repository → Entity (Product)
    ↑ returns entity
Service → converts to DTO (ProductDTO)
    ↑ returns DTO
Controller → wraps in ResponseEntity
    ↑ HTTP Response (JSON)
Client receives JSON
```

## Next Steps (What to Learn After This)

1. **Validation** — Add `@Valid`, `@NotNull`, `@Size` to validate input
2. **Update endpoint** — Add `PUT /api/products/{id}` to update a product
3. **Pagination** — Use `Pageable` for large datasets
4. **Model mapping** — Use MapStruct instead of manual entity↔DTO conversion
5. **Database migrations** — Use Flyway or Liquibase instead of `ddl-auto=update`
6. **Security** — Add Spring Security for authentication
7. **Testing** — Write unit tests with `@DataJpaTest` and `@SpringBootTest`
