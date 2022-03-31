drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `customers` (`first_name`, `surname`) VALUES ('Billy', 'Bob');
INSERT INTO `customers` (`first_name`, `surname`) VALUES ('Jimmy', 'Smith');
INSERT INTO `customers` (`first_name`, `surname`) VALUES ('Jason', 'Bourn');
INSERT INTO `customers` (`first_name`, `surname`) VALUES ('Bruce', 'Wayne');
INSERT INTO `customers` (`first_name`, `surname`) VALUES ('Wade', 'Wilson');

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(64) NOT NULL UNIQUE,
    `description` VARCHAR(255) NOT NULL,
    `price` DECIMAL(4 , 2) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `items` (`name`, `description`, `price`) VALUES ('Cheese', 'Yellow', '5');
INSERT INTO `items` (`name`, `description`, `price`) VALUES ('Steak', 'The best', '10');
INSERT INTO `items` (`name`, `description`, `price`) VALUES ('Grapes', 'Decent snack', '1');
INSERT INTO `items` (`name`, `description`, `price`) VALUES ('Sushi', 'Gross', '2');
INSERT INTO `items` (`name`, `description`, `price`) VALUES ('Chicken', 'Tastes like chicken', '7');

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`customer_id` INT NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (customer_id)
REFERENCES customers(id)
);

SELECT * FROM `orders`;
INSERT INTO `orders` (`customer_id`) VALUES ('1');
INSERT INTO `orders` (`customer_id`) VALUES ('2');
INSERT INTO `orders` (`customer_id`) VALUES ('3');
INSERT INTO `orders` (`customer_id`) VALUES ('4');
INSERT INTO `orders` (`customer_id`) VALUES ('5');

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
`quantity` INT NOT NULL DEFAULT 1,
`order_id` INT,
`item_id` INT,
PRIMARY KEY (`order_id`, `item_id`),
FOREIGN KEY (order_id)
REFERENCES orders(id),
FOREIGN KEY (item_id) 
REFERENCES items(id)
);

INSERT INTO `order_items` (`order_id`, `item_id`) VALUES ('1', '1');

SELECT `orders`.`id`, `customers`.`first_name`, `customers`.`surname`, `order_items`.`item_id`, `items`.`name`
FROM `orders` JOIN `customers` ON `orders`.`customer_id`=`customers`.`id` 
JOIN `order_items` ON `orders`.`id` = `order_items`.`order_id`
JOIN `items` ON `items`.`id` = `order_items`.`item_id` WHERE `orders`.`id` = '1'