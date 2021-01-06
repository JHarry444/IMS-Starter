USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT UNIQUE,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
	`item_id` INT(11) AUTO_INCREMENT NOT NULL UNIQUE,
	`item_name` VARCHAR(40) NULL DEFAULT NULL,
	`price` DECIMAL(10,2) NOT NULL,
	PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`order_id` INT(11) NOT NULL AUTO_INCREMENT UNIQUE,
	`customer_id` INT(11) NULL DEFAULT NULL,
	`item_id` INT(11) NULL DEFAULT NULL,
	`price` DECIMAL(10,2) NULL DEFAULT NULL,
	PRIMARY KEY (`order_id`),
	FOREIGN KEY (`customer_id`) REFERENCES `customers`(`customer_id`),
	FOREIGN KEY (`item_id`) REFERENCES `items`(`item_id`)
);
	
CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
	`orderitems_id` INT(11) AUTO_INCREMENT UNIQUE NOT NULL,
	`order_id` INT(11) NULL DEFAULT NULL,
	`item_id` INT(11) NULL DEFAULT NULL,
	`quantity` DECIMAL (11) NULL DEFAULT NULL,
	`order_price` DECIMAL(10,2) NULL DEFAULT NULL, 
	PRIMARY KEY (`orderitems_id`),
	FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`),
	FOREIGN KEY (`item_id`) REFERENCES `items`(`item_id`)
);






