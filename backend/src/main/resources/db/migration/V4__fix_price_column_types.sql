ALTER TABLE add_ons
    ALTER COLUMN price TYPE double precision;

ALTER TABLE orders
    ALTER COLUMN subtotal    TYPE double precision,
    ALTER COLUMN total_amount TYPE double precision;

ALTER TABLE order_items
    ALTER COLUMN unit_price  TYPE double precision,
    ALTER COLUMN add_on_price TYPE double precision;
