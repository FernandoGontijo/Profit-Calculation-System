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
