/*
 * =============================================
 * STEP 7: Controller - ProductController.java
 * =============================================
 * The Controller is the entry point for HTTP requests.
 * It receives requests from clients (browser, Postman, curl) and returns responses.
 *
 * FULL REQUEST FLOW:
 * Client sends HTTP request (e.g., GET /api/products)
 *   → Controller receives it and calls the Service
 *     → Service runs business logic and calls the Repository
 *       → Repository talks to the Database
 *       ← Repository returns Entity
 *     ← Service converts Entity to DTO and returns it
 *   ← Controller wraps DTO in ResponseEntity and sends HTTP response
 * Client receives JSON response
 *
 * KEY CONCEPTS:
 * - @RestController = @Controller + @ResponseBody
 *     @Controller → marks this as a Spring MVC controller
 *     @ResponseBody → return values are automatically converted to JSON
 *
 * - @RequestMapping("/api/products") → base URL path for all endpoints in this controller
 *
 * - HTTP Methods → CRUD mapping:
 *     GET    = Read    (fetch data)
 *     POST   = Create  (send new data)
 *     PUT    = Update  (modify existing data)
 *     DELETE = Delete  (remove data)
 *
 * - ResponseEntity → gives you full control over the HTTP response:
 *     status code (200, 201, 204, 404, etc.)
 *     headers
 *     body (the JSON data)
 */
package com.example.springbootfromzero.controller;

import com.example.springbootfromzero.dto.ProductDTO;
import com.example.springbootfromzero.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                      // This class handles HTTP requests and returns JSON
@RequestMapping("/api/products")    // All endpoints in this controller start with /api/products
@RequiredArgsConstructor            // Lombok: constructor injection for ProductService
public class ProductController {

    // Spring injects ProductServiceImpl here (because it implements ProductService)
    private final ProductService productService;

    /*
     * GET /api/products
     * Returns a list of ALL products.
     *
     * @GetMapping = handles HTTP GET requests
     * No path specified = uses the class-level @RequestMapping path (/api/products)
     *
     * ResponseEntity.ok() = HTTP 200 OK + body
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /*
     * GET /api/products/{id}
     * Returns a single product by its ID.
     *
     * @PathVariable → extracts the {id} from the URL
     * Example: GET /api/products/5 → id = 5
     *
     * If product not found → Service throws ResourceNotFoundException
     * (We'll handle that globally in Step 8)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /*
     * POST /api/products
     * Creates a new product.
     *
     * @RequestBody → Spring automatically converts the JSON body into a ProductDTO object
     * Example request body: {"name": "Phone", "description": "Smartphone", "price": 599.99}
     *
     * HttpStatus.CREATED = HTTP 201 (standard for "resource created successfully")
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /*
     * DELETE /api/products/{id}
     * Deletes a product by its ID.
     *
     * ResponseEntity.noContent() = HTTP 204 No Content
     * 204 means "success, but nothing to return" — standard for DELETE operations
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
