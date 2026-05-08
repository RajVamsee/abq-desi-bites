-- V1: Initial schema for ABQ Desi Bites
-- Flyway runs this automatically on first startup

CREATE TABLE menu_categories (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    sort_order  INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE menu_items (
    id            SERIAL PRIMARY KEY,
    category_id   INTEGER NOT NULL REFERENCES menu_categories(id),
    name          VARCHAR(150) NOT NULL,
    description   VARCHAR(500),
    price         NUMERIC(6,2) NOT NULL,
    is_available  BOOLEAN NOT NULL DEFAULT TRUE,
    is_weekend    BOOLEAN NOT NULL DEFAULT FALSE,
    sort_order    INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE contact_inquiries (
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(150) NOT NULL,
    phone        VARCHAR(20),
    message      TEXT NOT NULL,
    submitted_at TIMESTAMP NOT NULL DEFAULT NOW()
);
