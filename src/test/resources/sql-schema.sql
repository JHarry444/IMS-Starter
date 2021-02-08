DROP TABLE IF EXISTS `order_item`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS `items` (
	`item_id` INT (11) NOT NULL AUTO_INCREMENT,
	`item_name` VARCHAR (40) DEFAULT NULL,
	`item_price` INT (5) DEFAULT NULL,
	PRIMARY KEY (`item_id`)
);

DROP TABLE IF EXISTS `orders`;

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` INT (11) NOT NULL AUTO_INCREMENT,
	`id` INT (11) NOT NULL,
	`order_price` INT (11) NOT NULL,
	PRIMARY KEY (`order_id`),
	FOREIGN KEY (`id`) REFERENCES customers(`id`)
);

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE IF NOT EXISTS `order_item`(
	`order_item_id` INT (11) NOT NULL AUTO_INCREMENT,
	`order_id` INT (11) NOT NULL,
	`item_id` INT (11) NOT NULL,
    `id`  INT (11) NOT NULL,
	PRIMARY KEY (`order_item_id`),
	FOREIGN KEY (`order_id`) REFERENCES orders(`order_id`),
	FOREIGN KEY (`item_id`) REFERENCES items(`item_id`),
    FOREIGN KEY (`id`) REFERENCES orders(`id`)
);