DROP SCHEMA ims;
CREATE SCHEMA IF NOT EXISTS ims;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
`customer_id` INT(11) NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(40),
`surname` VARCHAR(40),
PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
`item_id` INT(11) NOT NULL AUTO_INCREMENT,
`item_name` VARCHAR(40) UNIQUE,
`price` DECIMAL(10, 2),
PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
`order_id` INT(11) NOT NULL AUTO_INCREMENT,
`customer_id` INT(11) NOT NULL,
PRIMARY KEY (`order_id`),
CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `ims`.`customers`(`customer_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
`order_item_id` INT(11) UNIQUE AUTO_INCREMENT NOT NULL,
`order_id` INT(11) NOT NULL, 
`item_id` INT(11) NOT NULL,
PRIMARY KEY (`order_item_id`),
CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `ims`.`orders`(`order_id`), 
CONSTRAINT `item_id` FOREIGN KEY (`item_id`) REFERENCES `ims`.`items`(`item_id`)
);