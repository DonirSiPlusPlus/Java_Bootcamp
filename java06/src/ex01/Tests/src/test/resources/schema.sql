DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    price INTEGER NOT NULL
);
