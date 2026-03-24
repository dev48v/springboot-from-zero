/*
 * =============================================
 * STEP 6: Service Implementation - ProductServiceImpl.java
 * =============================================
 * This is where the actual business logic lives.
 * It implements the ProductService interface from Step 5.
 *
 * KEY CONCEPTS:
 * - @Service → tells Spring "this is a service bean, manage it for me"
 *   Spring will create ONE instance and inject it wherever ProductService is needed
 *
 * - @RequiredArgsConstructor (Lombok) → generates a constructor for all "final" fields
 *   This is CONSTRUCTOR INJECTION — the recommended way to inject dependencies in Spring.
 *   Why constructor injection? → dependencies are required, immutable, and easy to test
 *
 * - @Transactional → wraps the method in a database transaction
 *   If anything fails, all DB changes in that method are rolled back
 *   readOnly=true → optimization hint for read-only queries (no write lock)
 *
 * - Entity ↔ DTO mapping: We convert between Product (entity) and ProductDTO manually
 *   In large projects, use MapStruct or ModelMapper libraries to automate this
 */
package com.example.springbootfromzero.service.impl;

import com.example.springbootfromzero.domain.Product;
import com.example.springbootfromzero.dto.ProductDTO;
import com.example.springbootfromzero.exception.ResourceNotFoundException;
import com.example.springbootfromzero.repository.ProductRepository;
import com.example.springbootfromzero.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service                    // Registers this class as a Spring bean (service layer)
@RequiredArgsConstructor    // Lombok: generates constructor → Spring injects ProductRepository automatically
public class ProductServiceImpl implements ProductService {

    // "final" + @RequiredArgsConstructor = constructor injection (Spring best practice)
    // Spring sees this class needs ProductRepository and injects it automatically
    private final ProductRepository productRepository;

    /*
     * Helper method: Convert Entity → DTO
     * We only expose the fields we want the client to see
     */
    private ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    /*
     * Helper method: Convert DTO → Entity
     * When the client sends data (POST), we convert it to an entity before saving
     */
    private Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return product;
    }

    @Override
    @Transactional(readOnly = true)  // Read-only transaction — DB can optimize this
    public List<ProductDTO> getAllProducts() {
        // productRepository.findAll() → SELECT * FROM products
        // .stream() → convert list to a stream for functional processing
        // .map(this::toDto) → convert each Product entity to ProductDTO
        // .collect(Collectors.toList()) → collect results back into a List
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        // findById returns Optional<Product> — it might be empty if ID doesn't exist
        // .orElseThrow() → if empty, throw our custom exception
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return toDto(product);
    }

    @Override
    @Transactional  // Write transaction — if save fails, everything rolls back
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = toEntity(productDTO);
        product.setCreatedAt(LocalDateTime.now());  // Set timestamp before saving

        // .save() → INSERT INTO products (...) VALUES (...)
        // It returns the saved entity WITH the auto-generated ID
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        // Check if product exists first — throw 404 if not
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        // deleteById → DELETE FROM products WHERE id = ?
        productRepository.deleteById(id);
    }
}
