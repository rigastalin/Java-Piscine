DROP schema IF EXISTS products cascade;

CREATE schema IF NOT EXISTS products;

CREATE TABLE IF NOT EXISTS products.product (
                 identifier INTEGER,
                 name VARCHAR(128) NOT NULL,
                 price INTEGER,
                 PRIMARY KEY (identifier)
);