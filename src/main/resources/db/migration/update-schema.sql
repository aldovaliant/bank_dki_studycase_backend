CREATE SEQUENCE IF NOT EXISTS stock_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE stock
(
    id                 BIGINT  NOT NULL,
    name               VARCHAR(255),
    quantity           INTEGER NOT NULL,
    item_serial_number VARCHAR(255),
    additional_info    JSONB,
    image_path         VARCHAR(255),
    created_at         TIMESTAMP WITHOUT TIME ZONE,
    updated_at         TIMESTAMP WITHOUT TIME ZONE,
    updated_by         INTEGER,
    CONSTRAINT pk_stock PRIMARY KEY (id)
);