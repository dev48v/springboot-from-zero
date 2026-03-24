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
