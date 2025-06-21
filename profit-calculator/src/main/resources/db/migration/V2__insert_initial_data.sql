-- Insert sample data for development and testing

-- Shipments
INSERT INTO shipment (status, created_at, updated_at, active)
VALUES
    ('PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    ('IN_PROGRESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE),
    ('DELIVERED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

-- Roles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_MANAGER');

-- Users
INSERT INTO users (username, password, enabled, created_at, updated_at)
VALUES
    ('user', '$2a$10$eCIHBYj3FScU40DYfVL.Q.zoOmbhLg8ZNKF9gXu1gHDmFljW14pT6', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('manager', '$2a$10$eCIHBYj3FScU40DYfVL.Q.zoOmbhLg8ZNKF9gXu1gHDmFljW14pT6', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- User-Role mappings
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON u.username = 'user' AND r.name = 'ROLE_USER';

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON u.username = 'manager' AND r.name = 'ROLE_MANAGER';

-- Incomes
INSERT INTO incomes (shipment_id, amount, created_at, updated_at, active)
SELECT s.id, 500.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'PENDING';

INSERT INTO incomes (shipment_id, amount, created_at, updated_at, active)
SELECT s.id, 750.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'IN_PROGRESS';

INSERT INTO incomes (shipment_id, amount, created_at, updated_at, active)
SELECT s.id, 300.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'DELIVERED';

-- Costs
INSERT INTO costs (shipment_id, amount, created_at, updated_at, active)
SELECT s.id, 200.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'PENDING';

INSERT INTO costs (shipment_id, amount, created_at, updated_at, active)
SELECT s.id, 50.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'PENDING';

INSERT INTO costs (shipment_id, amount, created_at, updated_at, active)
SELECT s.id, 600.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'IN_PROGRESS';

INSERT INTO costs (shipment_id, amount, created_at, updated_at, active)
SELECT s.id, 100.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'DELIVERED';

INSERT INTO costs (shipment_id, amount, created_at, updated_at, active)
SELECT s.id, 120.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'DELIVERED';

-- Profit/Loss calculations
INSERT INTO profit_loss (shipment_id, total_income, total_cost, calculated_profit, calculated_at, created_at, updated_at, active)
SELECT s.id, 500.00, 250.00, 250.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'PENDING';

INSERT INTO profit_loss (shipment_id, total_income, total_cost, calculated_profit, calculated_at, created_at, updated_at, active)
SELECT s.id, 750.00, 600.00, 150.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'IN_PROGRESS';

INSERT INTO profit_loss (shipment_id, total_income, total_cost, calculated_profit, calculated_at, created_at, updated_at, active)
SELECT s.id, 300.00, 220.00, 80.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE FROM shipment s WHERE s.status = 'DELIVERED';
