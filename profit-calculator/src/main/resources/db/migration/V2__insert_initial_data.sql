-- V2__insert_initial_data.sql
-- Insert sample data for development and testing

-- Shipments
INSERT INTO shipment (id, status, created_at, updated_at, active)
VALUES
    (1, 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (2, 'IN_PROGRESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (3, 'DELIVERED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

-- Incomes
INSERT INTO incomes (id, shipment_id, amount, created_at, updated_at, active)
VALUES
    (1, 1, 500.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (2, 2, 750.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (3, 3, 300.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

-- Costs
INSERT INTO costs (id, shipment_id, amount, created_at, updated_at, active)
VALUES
    (1, 1, 200.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (2, 1, 50.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (3, 2, 600.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (4, 3, 100.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (5, 3, 120.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

-- Profit/Loss calculations
INSERT INTO profit_loss (id, shipment_id, total_income, total_cost, calculated_profit, calculated_at, created_at, updated_at, active)
VALUES
    (1, 1, 500.00, 250.00, 250.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (2, 2, 750.00, 600.00, 150.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    (3, 3, 300.00, 220.00, 80.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_MANAGER');

-- Users
INSERT INTO users (id, username, password, enabled, created_at, updated_at)
VALUES
    (1, 'user', '$2a$10$7QOiH5ZkEoGmK8Z0yTWX3OOUpQ98iL7nEsG9EqR0xykOjNNj9UGYO', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'manager', '$2a$10$7QOiH5ZkEoGmK8Z0yTWX3OOUpQ98iL7nEsG9EqR0xykOjNNj9UGYO', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- User-Role mappings
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- user -> ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- manager -> ROLE_MANAGER