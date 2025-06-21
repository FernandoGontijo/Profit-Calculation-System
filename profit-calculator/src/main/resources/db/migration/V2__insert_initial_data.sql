-- V2__insert_initial_data.sql
-- Sample data for development/testing

-- Roles
INSERT INTO roles (name, created_at, updated_at)
VALUES ('ROLE_USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('ROLE_MANAGER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Users
INSERT INTO users (username, password, created_at, updated_at, active)
VALUES
 ('user', '$2a$10$eCIHBYj3FScU40DYfVL.Q.zoOmbhLg8ZNKF9gXu1gHDmFljW14pT6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
 ('manager', '$2a$10$eCIHBYj3FScU40DYfVL.Q.zoOmbhLg8ZNKF9gXu1gHDmFljW14pT6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

-- User-Role mappings
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON u.username = 'user' AND r.name = 'ROLE_USER';
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON u.username = 'manager' AND r.name = 'ROLE_MANAGER';

-- Shipments
INSERT INTO shipment (status, created_at, updated_at, active)
VALUES
    ('FAILED_DELIVERY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    ('IN_PROGRESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    ('DELIVERED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

-- Incomes (linked to shipments)
INSERT INTO incomes (shipment_id, amount, created_at, updated_at, active)
SELECT id, 500.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment WHERE status = 'FAILED_DELIVERY'
UNION ALL
SELECT id, 750.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment WHERE status = 'IN_PROGRESS'
UNION ALL
SELECT id, 300.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment WHERE status = 'DELIVERED';

-- Costs
INSERT INTO costs (shipment_id, amount, created_at, updated_at, active)
SELECT id, 200.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment WHERE status = 'FAILED_DELIVERY'
UNION ALL
SELECT id, 50.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment WHERE status = 'FAILED_DELIVERY'
UNION ALL
SELECT id, 600.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment WHERE status = 'IN_PROGRESS'
UNION ALL
SELECT id, 100.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment WHERE status = 'DELIVERED'
UNION ALL
SELECT id, 120.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment WHERE status = 'DELIVERED';

-- Profit/Loss calculations
INSERT INTO profit_loss (shipment_id, total_income, total_cost, calculated_profit, calculated_at, created_at, updated_at)
SELECT id, 500.00, 250.00, 250.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM shipment WHERE status = 'FAILED_DELIVERY'
UNION ALL
SELECT id, 750.00, 600.00, 150.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM shipment WHERE status = 'IN_PROGRESS'
UNION ALL
SELECT id, 300.00, 220.00, 80.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM shipment WHERE status = 'DELIVERED';
