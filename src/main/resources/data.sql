-- =============================================
-- STEP 8: Seed Data - data.sql
-- =============================================
-- Spring Boot automatically runs this file at startup IF:
--   1. It's in src/main/resources/
--   2. It's named "data.sql" (Spring looks for this exact name)
--   3. spring.jpa.hibernate.ddl-auto is set to "update" or "create"
--
-- This is useful for:
--   - Pre-populating test data during development
--   - Having data ready immediately after starting the app
--
-- NOTE: In production, use Flyway or Liquibase for database migrations instead.

INSERT INTO products (name, description, price, created_at) VALUES ('iPhone 15', 'Apple smartphone with A16 chip', 999.99, NOW());
INSERT INTO products (name, description, price, created_at) VALUES ('MacBook Pro', 'Laptop for developers', 1999.00, NOW());
INSERT INTO products (name, description, price, created_at) VALUES ('AirPods Pro', 'Wireless noise-cancelling earbuds', 249.99, NOW());
