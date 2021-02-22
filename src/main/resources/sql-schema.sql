drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
	`item_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(80) NOT NULL,
    `value` FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `cust_id` INT NOT NULL,
    `subtotal` FLOAT NOT NULL,
    FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`)
);

CREATE TABLE IF NOT EXISTS `order_items` (
	`order_items_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `item_id` INT NOT NULL,
	`quantity` INT NOT NULL,
    `order_id` INT NOT NULL,
     FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`),
     FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`)
);
