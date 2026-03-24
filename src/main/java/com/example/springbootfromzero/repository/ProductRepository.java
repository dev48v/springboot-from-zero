/*
 * =============================================
 * STEP 3: Repository - ProductRepository.java
 * =============================================
 * A Repository is the layer that talks to the database.
 * Spring Data JPA is MAGIC here — you just define an interface,
 * and Spring automatically creates the implementation for you at runtime!
 *
 * KEY CONCEPTS:
 * - JpaRepository<Product, Long> gives you these methods FOR FREE (no code needed):
 *     • findAll()       → SELECT * FROM products
 *     • findById(id)    → SELECT * FROM products WHERE id = ?
 *     • save(product)   → INSERT or UPDATE
 *     • deleteById(id)  → DELETE FROM products WHERE id = ?
 *     • count()         → SELECT COUNT(*) FROM products
 *     • existsById(id)  → SELECT EXISTS(...)
 *
 * - The two generic types are: <EntityClass, PrimaryKeyType>
 *   Product = the entity class, Long = the type of the @Id field
 *
 * WHY AN INTERFACE (not a class)?
 * Spring Data JPA generates the implementation at runtime using proxies.
 * You never write SQL for basic CRUD — Spring does it for you.
 * You CAN add custom queries later using method names or @Query annotation.
 */
package com.example.springbootfromzero.repository;

import com.example.springbootfromzero.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// That's it! Just extend JpaRepository and you get full CRUD operations.
// No @Repository annotation needed — Spring Data auto-detects interfaces extending JpaRepository.
public interface ProductRepository extends JpaRepository<Product, Long> {

    // BONUS: You can add custom finder methods just by naming them correctly!
    // Spring Data JPA parses the method name and generates the query.
    // Examples (uncomment to use):
    //
    // List<Product> findByName(String name);
    //     → SELECT * FROM products WHERE name = ?
    //
    // List<Product> findByPriceLessThan(BigDecimal price);
    //     → SELECT * FROM products WHERE price < ?
    //
    // List<Product> findByNameContainingIgnoreCase(String keyword);
    //     → SELECT * FROM products WHERE LOWER(name) LIKE LOWER('%keyword%')
}
