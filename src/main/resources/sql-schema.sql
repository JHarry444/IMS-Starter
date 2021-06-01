USE ims;

DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `ordered_items`;


CREATE TABLE IF NOT EXISTS customers (
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(60),
  surname VARCHAR(60),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS items (
  id BIGINT AUTO_INCREMENT,
  company VARCHAR(60),
  product VARCHAR(60),
  price DECIMAL(9,2),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders (
  id BIGINT AUTO_INCREMENT,
  customer_id BIGINT NOT NULL,
  total DECIMAL(9,2),
  ordered_on DATE,
  PRIMARY KEY (id),
  FOREIGN KEY(customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS ordered_items (
  id BIGINT AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  item_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(order_id) REFERENCES orders(id),
  FOREIGN KEY(item_id) REFERENCES items(id)
);

