drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims`;


CREATE TABLE `ims`.`customers`
(
   `customer_id` int NOT NULL AUTO_INCREMENT,
   `first_name` varchar (45) NOT NULL,
   `last_name` varchar (45) NOT NULL,
   `email_address` varchar (100) NOT NULL,
   `address_line_1` varchar (60) NOT NULL,
   `postcode` varchar (8) NOT NULL,
   PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items`
(
   `product_id` int NOT NULL AUTO_INCREMENT,
   `product_name` varchar (70) NOT NULL,
   `artist_name` varchar (70) NOT NULL,
   `release_date` date NOT NULL,
   `product_price` double NOT NULL,
   `product_qty` double NOT NULL,
   PRIMARY KEY (`product_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders`
(
   `order_id` int NOT NULL AUTO_INCREMENT,
   `customer_id` int NOT NULL,
   `product_id` int NOT NULL,
   `order_qty` int NOT NULL,
   `total_price` double NOT NULL,
   `order_status` boolean DEFAULT FALSE,
   PRIMARY KEY (`order_id`),
   FOREIGN KEY (`customer_id`) REFERENCES `ims`.`customers` (`customer_id`),
   FOREIGN KEY (`product_id`) REFERENCES `ims`.`items` (`product_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orderline`
(
   `orderline_id` int NOT NULL AUTO_INCREMENT,
   `order_id` int,
   `product_id` int,
   PRIMARY KEY (`orderline_id`),
   FOREIGN KEY (`order_id`) REFERENCES `ims`.`orders` (`order_id`),
   FOREIGN KEY (`product_id`) REFERENCES `ims`.`items` (`product_id`)
);