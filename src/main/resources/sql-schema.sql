CREATE SCHEMA IF NOT EXISTS ims;
USE ims;

CREATE TABLE IF NOT EXISTS ims.customers (
    customer_id INT UNIQUE NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) NULL DEFAULT NULL,
    surname VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS ims.orders (
    order_id INT UNIQUE NOT NULL AUTO_INCREMENT,
    customer_id INT NOT NULL,
    date DATETIME DEFAULT NOW(),
    PRIMARY KEY (order_id),
    FOREIGN KEY(customer_id) REFERENCES ims.customers(customer_id)
);

CREATE TABLE IF NOT EXISTS ims.items (
    item_id INT UNIQUE NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    stock INT DEFAULT 0,
    price DECIMAL(5,2),
    PRIMARY KEY (item_id)
);

CREATE TABLE IF NOT EXISTS ims.order_item (
    item_id INT NOT NULL,
    order_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (item_id) REFERENCES ims.items(item_id),
	FOREIGN KEY (order_id) REFERENCES ims.orders(order_id)
);
