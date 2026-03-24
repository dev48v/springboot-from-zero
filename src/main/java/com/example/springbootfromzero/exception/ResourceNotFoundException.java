/*
 * =============================================
 * STEP 6: Custom Exception - ResourceNotFoundException.java
 * =============================================
 * A custom exception for when a requested resource doesn't exist in the database.
 * For example: GET /api/products/999 → product with ID 999 doesn't exist → throw this.
 *
 * KEY CONCEPTS:
 * - WHY a custom exception instead of just returning null?
 *     1. CLARITY → "ResourceNotFoundException" is self-explanatory
 *     2. HTTP MAPPING → We can map this to HTTP 404 (Not Found) in the exception handler
 *     3. CONSISTENCY → Every "not found" case behaves the same way across the app
 *
 * - extends RuntimeException (not Exception):
 *     RuntimeException = unchecked → you don't need try/catch everywhere
 *     Exception = checked → forces try/catch (too verbose for this use case)
 */
package com.example.springbootfromzero.exception;

public class ResourceNotFoundException extends RuntimeException {

    // Constructor takes a message like "Product not found with id 42"
    public ResourceNotFoundException(String message) {
        super(message);  // Pass the message to RuntimeException's constructor
    }
}
