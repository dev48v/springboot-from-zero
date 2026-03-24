/*
 * =============================================
 * STEP 5: Service Interface - ProductService.java
 * =============================================
 * The Service layer contains your BUSINESS LOGIC.
 * It sits between the Controller (HTTP) and the Repository (Database).
 *
 * Request flow:  Client → Controller → Service → Repository → Database
 *
 * KEY CONCEPTS:
 * - WHY an interface?
 *     1. ABSTRACTION    → Controller doesn't know/care HOW products are fetched
 *     2. TESTABILITY    → You can create a mock implementation for testing
 *     3. FLEXIBILITY    → You can swap implementations without changing the controller
 *     4. SPRING PATTERN → This is the standard Spring Boot convention
 *
 * - WHY a separate Service layer (why not call Repository directly from Controller)?
 *     1. BUSINESS LOGIC → Service is where you put validation, calculations, transformations
 *     2. TRANSACTIONS   → Service methods can be wrapped in database transactions
 *     3. REUSABILITY    → Multiple controllers can use the same service
 *     4. SINGLE RESPONSIBILITY → Controller handles HTTP, Service handles business logic
 *
 * - In the next step (Step 6), we'll create ProductServiceImpl that implements this interface.
 */
package com.example.springbootfromzero.service;

import com.example.springbootfromzero.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    // Get all products from the database and return as DTOs
    List<ProductDTO> getAllProducts();

    // Get a single product by its ID, throw exception if not found
    ProductDTO getProductById(Long id);

    // Create a new product (accepts DTO, returns saved DTO with generated ID)
    ProductDTO createProduct(ProductDTO productDTO);

    // Delete a product by ID, throw exception if not found
    void deleteProduct(Long id);
}
