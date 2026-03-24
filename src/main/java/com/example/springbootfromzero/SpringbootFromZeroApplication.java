/*
 * =============================================
 * STEP 1: The Main Application Class
 * =============================================
 * This is the entry point of your Spring Boot application.
 * When you run this class, Spring Boot starts up and does everything:
 * - Starts an embedded Tomcat web server
 * - Scans for components (@Controller, @Service, @Repository, etc.)
 * - Auto-configures everything based on your dependencies
 *
 * KEY CONCEPTS:
 * - @SpringBootApplication = combines 3 annotations:
 *     1. @Configuration     → marks this class as a source of bean definitions
 *     2. @EnableAutoConfiguration → tells Spring Boot to auto-configure based on dependencies
 *     3. @ComponentScan     → scans this package and sub-packages for Spring components
 * - SpringApplication.run() → bootstraps the entire application
 */
package com.example.springbootfromzero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This single annotation does all the magic — it sets up your entire Spring Boot app
@SpringBootApplication
public class SpringbootFromZeroApplication {

    public static void main(String[] args) {
        // This line starts the embedded server and initializes all Spring components
        SpringApplication.run(SpringbootFromZeroApplication.class, args);
    }

}
