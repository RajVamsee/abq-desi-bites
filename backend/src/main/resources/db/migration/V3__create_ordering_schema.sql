CREATE TABLE add_ons (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    price       DECIMAL(6,2) NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO add_ons (name, price) VALUES ('Ghee / Butter', 1.00);

CREATE TABLE orders (
    id                       SERIAL PRIMARY KEY,
    customer_name            VARCHAR(150) NOT NULL,
    customer_email           VARCHAR(255) NOT NULL,
    customer_phone           VARCHAR(20),
    pickup_time              VARCHAR(50),
    special_instructions     TEXT,
    subtotal                 DECIMAL(8,2) NOT NULL,
    total_amount             DECIMAL(8,2) NOT NULL,
    status                   VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    stripe_payment_intent_id VARCHAR(255),
    created_at               TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE order_items (
    id           SERIAL PRIMARY KEY,
    order_id     INTEGER NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    menu_item_id INTEGER NOT NULL REFERENCES menu_items(id),
    item_name    VARCHAR(200) NOT NULL,
    unit_price   DECIMAL(6,2) NOT NULL,
    quantity     INTEGER NOT NULL DEFAULT 1,
    add_on_id    INTEGER REFERENCES add_ons(id),
    add_on_name  VARCHAR(100),
    add_on_price DECIMAL(6,2)
);
