/*
 * =============================================
 * STEP 4: DTO (Data Transfer Object) - ProductDTO.java
 * =============================================
 * A DTO is a simple object used to transfer data between layers.
 * It's what your API sends back to the client (the JSON response).
 *
 * KEY CONCEPTS:
 * - WHY not just return the Entity directly?
 *     1. SECURITY   → Entity might have sensitive fields (password, internal IDs) you don't want to expose
 *     2. CONTROL    → You choose exactly which fields the client sees
 *     3. DECOUPLING → If you change the Entity (add a DB column), the API response doesn't break
 *     4. FLEXIBILITY → You can combine data from multiple entities into one DTO
 *
 * - RULE OF THUMB:
 *     Entity = database shape (how data is stored)
 *     DTO    = API shape (how data is sent to the client)
 *
 * - In real projects, you might have separate DTOs:
 *     ProductCreateDTO (for POST requests — no id field)
 *     ProductResponseDTO (for GET responses — includes id)
 *     For simplicity, we use one DTO here.
 */
package com.example.springbootfromzero.dto;

import lombok.*;
import java.math.BigDecimal;

@Data                   // Lombok: generates getters, setters, toString, equals, hashCode
@NoArgsConstructor      // Lombok: no-arg constructor (needed for JSON deserialization)
@AllArgsConstructor     // Lombok: constructor with all fields
public class ProductDTO {

    private Long id;             // Included so GET responses show the product ID

    private String name;         // Product name

    private String description;  // Product description

    private BigDecimal price;    // Product price

    // Notice: NO "createdAt" field here!
    // That's the point of DTOs — we control what the client sees.
    // The entity has createdAt (stored in DB), but we don't expose it in the API.
}
