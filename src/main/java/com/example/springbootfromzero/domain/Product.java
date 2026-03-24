/*
 * =============================================
 * STEP 2: Entity (Domain Model) - Product.java
 * =============================================
 * An Entity is a Java class that maps to a database table.
 * Each instance of this class = one row in the "products" table.
 * JPA (Java Persistence API) handles the mapping between Java objects and DB rows.
 *
 * KEY CONCEPTS:
 * - @Entity    → tells JPA "this class represents a database table"
 * - @Table     → specifies the exact table name in the database
 * - @Id        → marks the primary key field
 * - @GeneratedValue → the database auto-generates the ID (auto-increment)
 * - @Column    → customizes how a field maps to a DB column (name, nullable, etc.)
 *
 * WHY ENTITIES?
 * Instead of writing raw SQL like "SELECT * FROM products",
 * you work with Java objects. JPA translates your Java code into SQL behind the scenes.
 */
package com.example.springbootfromzero.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity                          // This class is a JPA entity → it maps to a DB table
@Table(name = "products")       // The table name in MySQL will be "products"
@Data                            // Lombok: auto-generates getters, setters, toString, equals, hashCode
@NoArgsConstructor               // Lombok: generates a no-argument constructor (required by JPA)
@AllArgsConstructor              // Lombok: generates a constructor with all fields
@Builder                         // Lombok: lets you create objects like Product.builder().name("Phone").build()
public class Product {

    @Id                          // This field is the PRIMARY KEY of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GenerationType.IDENTITY = MySQL auto-increment (1, 2, 3, ...)
    // Other strategies: SEQUENCE (Oracle/Postgres), AUTO (let Hibernate decide)
    private Long id;

    private String name;         // Maps to column "name" (VARCHAR) — column name = field name by default

    private String description;  // Maps to column "description" (VARCHAR)

    private BigDecimal price;    // BigDecimal is better than double for money (no floating-point errors)

    @Column(name = "created_at") // Custom column name — without this, it would be "createdAt" (camelCase)
    private LocalDateTime createdAt;  // Java 8+ date/time API — better than old java.util.Date
}
