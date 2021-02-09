Drop schema `ims`;

create schema if not exists `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
	`item_id` INT(11) NOT NULL AUTO_INCREMENT,
	`item_name` VARCHAR(40) DEFAULT NULL,
	`item_cost` INT(11) DEFAULT NULL,
	`item_desc` VARCHAR(200) DEFAULT NULL,
	PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` INT(11) NOT NULL AUTO_INCREMENT,
	`cust_id` INT(11) NOT NULL,
	FOREIGN KEY (`cust_id`) REFERENCES customers(`id`),
    PRIMARY KEY (`order_id`)
);


CREATE TABLE IF NOT EXISTS `orders_items` (
	`order_item_id` INT(11) NOT NULL auto_increment,
    `order_id` INT(11) NOT NULL,
    `item_id` INT(11) DEFAULT NULL,
    `quantity` INT(11) DEFAULT NULL,
    FOREIGN KEY (`order_id`) REFERENCES orders(`order_id`),
    FOREIGN key (`item_id`) REFERENCES items(`item_id`),
    PRIMARY KEY (`order_item_id`)
    
);