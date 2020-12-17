drop schema ims_project;
CREATE SCHEMA IF NOT EXISTS `ims_project`;
USE `ims_project` ;

CREATE TABLE `ims_project`.`customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `address_line_1` varchar(60) NOT NULL,
  `postcode` varchar(8) NOT NULL,
  PRIMARY KEY (`customer_id`)
)


CREATE TABLE IF NOT EXISTS `ims_project`.`products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(70) NOT NULL,
  `artist` varchar(70) NOT NULL,
  `release_date` date NOT NULL,
  `product_price` decimal(10,2) NOT NULL,
  `product_qty` int NOT NULL,
  PRIMARY KEY (`product_id`)
)

CREATE TABLE IF NOT EXISTS `ims_project`.`orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `product_id` int NOT NULL,
  `order_qty` int NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `product_id_idx` (`product_id`),
  KEY `customer_id_idx` (`customer_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) 

CREATE TABLE IF NOT EXISTS `ims_project`.`orderline` (
  `orderline_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`orderline_id`)
)

