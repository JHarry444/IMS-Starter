drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) DEFAULT NULL,
	`price` FLOAT(20) DEFAULT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`orderid` INT(11) NOT NULL AUTO_INCREMENT,
	`customerid` INT(11) NOT NULL,
	`itemid` INT(11) NOT NULL,
	`quantity` INT(15) NOT NULL,
	PRIMARY KEY(orderid),
	FOREIGN KEY(customerid) REFERENCES customers(id),
	FOREIGN KEY(itemid) REFERENCES items(id)
); 