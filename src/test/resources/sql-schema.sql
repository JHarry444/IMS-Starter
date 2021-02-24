DROP TABLE IF EXISTS `orderdetails`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) DEFAULT NOT NULL,
	`price` FLOAT(20) DEFAULT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`customerid` INT(11) NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`customerid`) REFERENCES customers(`id`),
); 

CREATE TABLE IF NOT EXISTS `ims`.`orderdetails` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`orderid` INT(11) NOT NULL,
	`itemid` INT(11) NOT NULL,
	`quantity` INT(15) NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`orderid`) REFERENCES orders(`id`),
	FOREIGN KEY(`itemid`) REFERENCES items(`id`)
);
