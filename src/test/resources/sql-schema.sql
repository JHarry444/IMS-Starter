drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE `ims`.`customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `address_line_1` varchar(60) NOT NULL,
  `postcode` varchar(8) NOT NULL,
  PRIMARY KEY (`customer_id`)
)


CREATE TABLE IF NOT EXISTS `ims`.`items` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(70) NOT NULL,
  `artist` varchar(70) NOT NULL,
  `release_date` date NOT NULL,
  `product_price` decimal(10,2) NOT NULL,
  `product_qty` int NOT NULL,
  PRIMARY KEY (`product_id`)
)

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `product_id` int NOT NULL,
  `order_qty` int NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `order_status` boolean NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `product_id_idx` (`product_id`),
  KEY `customer_id_idx` (`customer_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `items` (`product_id`)
) 
