DROP TABLE `orders`;
DROP TABLE `items`;
DROP TABLE `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
	`item_id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(80) NOT NULL,
    `value` FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS `orders` (
	`order_id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `cust_id` INT(11) NOT NULL,
	`item_id` INT(11) NOT NULL,
	`quantity` INT(11) NOT NULL,
    FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
	FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`) ON DELETE CASCADE
);
