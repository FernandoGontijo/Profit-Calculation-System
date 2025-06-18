-- V1__init.sql
-- Initial schema creation with constraints and indexes

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);


CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);


CREATE TABLE shipment (
    id INT PRIMARY KEY,
    status VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    active BOOLEAN NOT NULL,
    CONSTRAINT chk_shipment_active CHECK (active IN (TRUE, FALSE))
);

CREATE TABLE costs (
    id INT PRIMARY KEY,
    shipment_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    active BOOLEAN NOT NULL,
    CONSTRAINT fk_costs_shipment FOREIGN KEY (shipment_id) REFERENCES shipment(id),
    CONSTRAINT chk_costs_amount CHECK (amount >= 0),
    CONSTRAINT chk_costs_active CHECK (active IN (TRUE, FALSE))
);

CREATE INDEX idx_costs_shipment_id ON costs(shipment_id);

CREATE TABLE incomes (
    id INT PRIMARY KEY,
    shipment_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    active BOOLEAN NOT NULL,
    CONSTRAINT fk_incomes_shipment FOREIGN KEY (shipment_id) REFERENCES shipment(id),
    CONSTRAINT chk_incomes_amount CHECK (amount >= 0),
    CONSTRAINT chk_incomes_active CHECK (active IN (TRUE, FALSE))
);

CREATE INDEX idx_incomes_shipment_id ON incomes(shipment_id);

CREATE TABLE profit_loss (
    id INT PRIMARY KEY,
    shipment_id INT NOT NULL,
    total_income DECIMAL(10,2),
    total_cost DECIMAL(10,2),
    calculated_profit DECIMAL(10,2),
    calculated_at TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    active BOOLEAN NOT NULL,
    CONSTRAINT fk_profit_shipment FOREIGN KEY (shipment_id) REFERENCES shipment(id),
    CONSTRAINT chk_profit_active CHECK (active IN (TRUE, FALSE))
);

CREATE INDEX idx_profit_shipment_id ON profit_loss(shipment_id);
