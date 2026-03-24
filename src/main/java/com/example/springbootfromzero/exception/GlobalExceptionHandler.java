/*
 * =============================================
 * STEP 8: Global Exception Handler
 * =============================================
 * Instead of handling exceptions in every controller method,
 * we handle them in ONE place. This class catches exceptions
 * thrown anywhere in the app and returns proper HTTP error responses.
 *
 * KEY CONCEPTS:
 * - @ControllerAdvice → makes this class a GLOBAL exception handler
 *   It "advises" all controllers — any exception thrown in any controller
 *   gets caught here automatically
 *
 * - @ExceptionHandler → specifies which exception type this method handles
 *   You can have multiple methods for different exception types
 *
 * - WITHOUT this class:
 *   If ResourceNotFoundException is thrown, Spring returns a generic 500 error
 *   with a stack trace (ugly and exposes internals)
 *
 * - WITH this class:
 *   ResourceNotFoundException → clean 404 response with our message
 *   Any other exception → clean 500 response without stack trace
 */
package com.example.springbootfromzero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice  // This class handles exceptions globally across ALL controllers
public class GlobalExceptionHandler {

    /*
     * Handles ResourceNotFoundException → returns HTTP 404
     * Example: GET /api/products/999 where product 999 doesn't exist
     * Response: { "status": 404, "error": "Not Found", "message": "Product not found with id 999", "timestamp": "..." }
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());    // 404
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());                 // "Product not found with id 999"
        error.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /*
     * Catch-all handler for any other unexpected exceptions → returns HTTP 500
     * This prevents stack traces from leaking to the client (security best practice)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());  // 500
        error.put("error", "Internal Server Error");
        error.put("message", "Something went wrong: " + ex.getMessage());
        error.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
