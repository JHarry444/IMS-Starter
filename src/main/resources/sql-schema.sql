drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims`;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NULL DEFAULT NULL,
    `item_price` DOUBLE NULL DEFAULT NULL,
    `item_quantity` INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_customer_id` FOREIGN KEY (`fk_customer_id`) REFERENCES customers(`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders_items` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_order_id` INT(11) NOT NULL,
    `fk_item_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_order_id` FOREIGN KEY (`fk_order_id`) REFERENCES orders(`id`),
    CONSTRAINT `fk_item_id` FOREIGN KEY (`fk_item_id`) REFERENCES items(`id`)
);